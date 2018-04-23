package fr.islandswars.core.bukkit.command.wrapper;

import fr.islandswars.api.cmd.CommandSerializer;
import fr.islandswars.api.cmd.lang.Compound;
import fr.islandswars.api.cmd.lang.Opt;
import fr.islandswars.api.cmd.lang.Serial;
import fr.islandswars.core.bukkit.command.CommandSerializers;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.StringJoiner;
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
 * Created the 27/03/2018 at 17:16
 * @since 0.2.9
 */
public class MethodCommandWrapper extends LabelDispatcher {

	private final Method                             method;
	private final int                                parametersCount;
	private final StringJoiner                       helpCompound;
	private       TIntObjectMap<CommandSerializer>   serializers;
	private       TIntObjectMap<Map<String, Object>> choiceLists;
	private       int                                optStart;

	private Class<?>             arrayType; //The array type
	private CommandSerializer<?> arraySerializer; //The array serializer
	private Map<String, Object>  arrayChoice; //The array choice list

	MethodCommandWrapper(String label, Method method, Compound compound) {
		super(compound.label().isEmpty() ? method.getName().toLowerCase() : compound.label(), compound.aliases());

		if ((method.getModifiers() & Modifier.STATIC) == 0)
			throw new CommandException(method + " must be static");

		Class<?> returnType = method.getReturnType();
		if (returnType != void.class)
			throw new CommandException(method + " cannot return " + returnType);

		this.helpCompound = new StringJoiner(" ").add("/" + label).add(getLabel());
		this.parametersCount = method.getParameterCount() - 1;
		this.serializers = new TIntObjectHashMap<>();
		this.choiceLists = new TIntObjectHashMap<>();
		super.description = compound.description();
		this.optStart = -1;

		this.method = method;

		try {
			serializeParameters();
			super.help = helpCompound.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dispatch(CommandSender player, String[] args, int loc) throws ReflectiveOperationException {
		int count = args.length - loc;
		if ((optStart == -1 ? count < parametersCount : count < optStart) || (arrayType == null && count > parametersCount))
			sendHelp(player);
	}

	private String registerSerializer(Serial serAnnotation, int i, Class<?> type, int max) throws ReflectiveOperationException {
		if (i == max - 1 && type.isArray()) {
			arrayType = type.getComponentType();

			if (serAnnotation != null) {
				arraySerializer = CommandSerializers.serializerOf(arrayType, serAnnotation.value());
				return valueTypeOfSerializer(arraySerializer, arrayType) + "...";
			} else if (arrayType.isEnum()) {
				arrayChoice = CommandChoiceLists.getFromEnum(arrayType);
				return arrayChoice.keySet().stream().collect(Collectors.joining("|")) + "...";
			}

			CommandSerializer componentSerializer = CommandSerializers.getSerializer(type);

			if (componentSerializer != null)
				arraySerializer = componentSerializer;

			return valueTypeOfSerializer(arraySerializer, arrayType) + "...";
		}

		if (serAnnotation != null) {
			CommandSerializer<?> serializer = CommandSerializers.serializerOf(type, serAnnotation.value());
			serializers.put(i, serializer);
			return valueTypeOfSerializer(serializer, type);
		} else if (type != String.class) {
			if (type.isEnum()) {
				choiceLists.put(i, CommandChoiceLists.getFromEnum(type));
				return type.getSimpleName();
			}

			CommandSerializer serializer = CommandSerializers.getSerializer(type);
			if (serializer != null) {
				serializers.put(i, serializer);
				return valueTypeOfSerializer(serializer, type);
			}

			throw new CommandException("Don't know how to serialize " + type);
		}
		return "String";
	}

	private void sendHelp(CommandSender sender) {
		sender.sendMessage("Wrong command sintax!");
		sender.sendMessage(" - " + help);
		if (!super.description.isEmpty())
			sender.sendMessage(description);
	}

	private void serializeParameters() throws Exception {
		Parameter[] parameters = method.getParameters();
		boolean     optReach   = false;

		if (parameters.length >= 1 && !CommandSender.class.isAssignableFrom(parameters[0].getType()))
			throw new CommandException("Command sender type " + parameters[0].getType() + " isn't an IslandsPlayer");

		for (int i = 1; i < parameters.length; i++) {
			Parameter param     = parameters[i];
			Class<?>  type      = param.getType();
			Serial    serial    = type.getAnnotation(Serial.class);
			String    valueType = registerSerializer(serial, i, type, parameters.length);
			Opt       optional  = param.getAnnotation(Opt.class);

			if (optional != null) {
				helpCompound.add("<" + param.getName().toLowerCase() + (type.isArray() ? "..." : "") + ">");
				if (!optReach) {
					optStart = i;
					optReach = true;
				}
				if (type.isPrimitive()) //Primitives are not nullable, so cannot be optionals
					throw new CommandException("Optional value(s) cannot be primitives");

			} else if (optReach) //The value is not optional but the previous value was
				throw new CommandException("Optional value(s) must be last value(s)");
			else
				helpCompound.add(param.getName().toLowerCase() + (type.isArray() ? "..." : ""));
		}
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
