package fr.islandswars.core.bukkit.command.wrapper;

import fr.islandswars.api.cmd.CommandSerializer;
import fr.islandswars.api.cmd.lang.Arg;
import fr.islandswars.api.cmd.lang.Compound;
import fr.islandswars.api.cmd.lang.Opt;
import fr.islandswars.api.cmd.lang.Serial;
import fr.islandswars.core.bukkit.command.CommandSerializers;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

/**
 * File <b>MethodCommandWrapper</b> located on fr.islandswars.core.bukkit.command.wrapper
 * MethodCommandWrapper is a part of Islands Wars - Api.
 * <p>
 * Copyright (c) 2017 - 2018 Islands Wars.
 * <p>
 * Islands Wars - Api is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <a href="http://www.gnu.org/licenses/">GNU GPL license</a>.
 * <p>
 *
 * @author SkyBeastMC
 * @author Valentin Burgaud (Xharos), {@literal <xharos@islandswars.fr>}
 * Created the 17/03/2018 at 18:23
 * @since 0.2.9
 */
public class MethodCommandWrapper extends CommandWrapper {

	private final Method                             method;
	private       TIntObjectMap<CommandSerializer>   serializers;
	private       TIntObjectMap<Map<String, Object>> choiceLists;
	private       int                                optStart; //At which argument starts optionals? -- -1 if never

	/*
	 * Array -- Only if last argument is array
	 */
	private Class<?>             arrayType; //The array type
	private CommandSerializer<?> arraySerializer; //The array serializer
	private Map<String, Object>  arrayChoice; //The array choice list

	MethodCommandWrapper(Method method, Compound compound) {
		super(compound.label().isEmpty() ? method.getName().toLowerCase() : compound.label(), compound.aliases());
		this.serializers = new TIntObjectHashMap<>();
		this.choiceLists = new TIntObjectHashMap<>();
		this.method = method;
		this.optStart = -1;

		if ((method.getModifiers() & Modifier.STATIC) == 0)
			throw new CommandException(method + " must be static");

		Class<?> returnType = method.getReturnType();
		if (returnType != void.class && returnType != boolean.class)
			throw new CommandException(method + " cannot return " + returnType);

		setupSenderType();
		try {
			serializeParameters();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean dispatch(CommandSender sender, String[] cmd, int loc) throws ReflectiveOperationException {

		int count = cmd.length - loc; //Argument count
		//if (!checkArgumentCount(count, sender, cmd, loc, rootLabel))//Not enough / Too much arguments
		//	return false;

		Object[] args = new Object[method.getParameterCount()];
		args[0] = sender; //First parameter of the method is the sender

		for (int i = 0; i < count; i++) {
			String arg = cmd[loc + i];

			//-- Serializer handling
			CommandSerializer<?> serializer = getSerializer(i);
			if (serializer != null) {
				try {
					args[i + 1] = serializer.serialize(arg);
					continue;
				} catch (ReflectiveOperationException e) {
					e.printStackTrace();
					return false;
				}
			}

			//-- Choice list handling
			Map<String, Object> choice = getChoiceList(i);
			if (choice != null) {
				Object o = choice.get(arg);
				if (o == null) {
					//showHelp(sender, cmd, loc, rootLabel);
					return false;
				}
				args[i + 1] = o;
				continue;
			}

			//-- Array handling
			if (arrayType != null && i == method.getParameterCount() - 2) {
				int    size  = count - i;
				Object array = Array.newInstance(arrayType, size);


				if (arraySerializer != null) //Serializer
					for (int j = 0; j < size; j++) {
						try {
							Array.set(array, j, arraySerializer.serialize(cmd[loc + i + j]));
						} catch (ReflectiveOperationException e) {
							e.printStackTrace();
							return false;
						}
					}
				else if (arrayChoice != null) //Choice list
					for (int j = 0; j < size; j++) {
						Object o = arrayChoice.get(cmd[loc + i + j]);
						if (o == null) {
							//showHelp(sender, cmd, loc, rootLabel);
							return false;
						}

						Array.set(array, j, o);
					}
				else
					for (int j = 0; j < size; j++) //String
						Array.set(array, j, cmd[loc + i + j]);

				args[method.getParameterCount() - 1] = array;
				break;
			}

			//-- Arg is a String
			args[i + 1] = arg;
		}

		Object ret = method.invoke(null, args);
		return ret == null || (Boolean) ret;
	}

	private String getArgAnnotationValue(Arg annotation, Function<Arg, String> fn, String defaultValue) {
		return annotation == null ? defaultValue : getFirstOrDefault(fn.apply(annotation), defaultValue);
	}

	private Map<String, Object> getChoiceList(int i) {
		if (choiceLists != null)
			return choiceLists.get(i);
		return null;
	}

	private String getFirstOrDefault(String first, String defaultValue) {
		return first.isEmpty() ? defaultValue : first;
	}

	private CommandSerializer getSerializer(int i) {
		if (serializers != null)
			return serializers.get(i);
		return null;
	}

	private String registerSerializer(Serial serAnnotation, int i, Class<?> type, int max) throws ReflectiveOperationException {
		if (i == max - 1 && type.isArray()) {
			arrayType = type.getComponentType();

			//The array has a Serialize annotation
			if (serAnnotation != null) {
				arraySerializer = CommandSerializers.serializerOf(arrayType, serAnnotation.value());

				return valueTypeOfSerializer(arraySerializer, arrayType) + "...";
			}
			//The array is a ChoiceList array
			else if (arrayType.isEnum()) {
				arrayChoice = CommandChoiceLists.getFromEnum(arrayType);

				return arrayChoice.keySet().stream().collect(
						Collectors.joining("|")) + "...";
			}

			CommandSerializer componentSerializer = CommandSerializers.getSerializer(type);
			//The array needs default serializing
			if (componentSerializer != null)
				arraySerializer = componentSerializer;

			//The array is a String array
			return valueTypeOfSerializer(arraySerializer, arrayType) + "...";
		}

		//-- Serialize annotation handling
		if (serAnnotation != null) {
			CommandSerializer<?> serializer = CommandSerializers.serializerOf(type, serAnnotation.value());
			serializers.put(i, serializer);

			return valueTypeOfSerializer(serializer, type);
		} else if (type != String.class) {
			//-- ChoiceList handling
			if (type.isEnum()) {
				choiceLists.put(i, CommandChoiceLists.getFromEnum(type));

				return type.getSimpleName();
			}

			CommandSerializer serializer = CommandSerializers.getSerializer(type);
			//-- Default serialization
			if (serializer != null) {
				serializers.put(i, serializer);

				return valueTypeOfSerializer(serializer, type);
			}

			throw new CommandException("Don't know how to serialize " + type);
		}

		return "String";
	}

	private void serializeParameters() throws Exception {
		Parameter[] parameters = method.getParameters();


		boolean isOptionalDone = false; //Used to know if optional already started
		for (int i = 0; i < parameters.length - 1; i++) {
			Parameter param = parameters[i + 1]; //Don't use first parameter -- sender type
			Class<?>  type  = param.getType();


			//-- Serialization handling
			Serial serAnnotation = param.getAnnotation(Serial.class);
			String valueType     = registerSerializer(serAnnotation, i, type, parameters.length - 1);


			//-- Arg annotation handling
			Arg argAnnotation = param.getAnnotation(Arg.class);

			//Get fields from annotation or default
			String argType = getArgAnnotationValue(argAnnotation, Arg::type, valueType);
			String argName = getArgAnnotationValue(argAnnotation, Arg::value, param.getName());

			//-- Optional handling
			Opt optAnnotation = param.getAnnotation(Opt.class);

			boolean paramHasOptional = optAnnotation != null;

			if (paramHasOptional) {
				if (!isOptionalDone) {
					optStart = i; //Optional parameters from this index
					isOptionalDone = true;
				}
				if (type.isPrimitive()) //Primitives are not nullable, so cannot be optionals
					throw new CommandException("Optional value(s) cannot be primitives");
			} else if (isOptionalDone) //The value is not optional but the previous value was
				throw new CommandException("Optional value(s) must be last value(s)");
		}
	}

	private void setupSenderType() {
		Class<?>[] parameters = method.getParameterTypes();

		if (parameters.length < 1)
			throw new CommandException("Method " + method + " doesn't accept any command sender");

		if (!CommandSender.class.isAssignableFrom(method.getParameterTypes()[0]))
			throw new CommandException("Command sender type " + parameters[0] + " cannot be resolved on method " + method);
	}

	private String valueTypeOfSerializer(CommandSerializer<?> serializer, Class<?> clazz) {
		if (serializer == null)
			return "String";

		String valueType = serializer.valueType();
		if (valueType == null)
			throw new CommandException("Cannot have a null valueType!");
		return valueType.isEmpty() ? clazz.getSimpleName() : valueType;
	}
}
