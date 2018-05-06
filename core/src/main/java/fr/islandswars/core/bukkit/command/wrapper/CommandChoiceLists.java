package fr.islandswars.core.bukkit.command.wrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.bukkit.command.CommandException;

/**
 * File <b>CommandChoiceLists</b> located on fr.islandswars.core.bukkit.command.wrapper
 * CommandChoiceLists is a part of Islands Wars - Api.
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
 * Created the 20/03/2018 at 13:23
 * @since 0.2.9
 */
public class CommandChoiceLists {

	private static final Map<Class<?>, Map<String, Object>> ENUMS = new HashMap<>();

	private CommandChoiceLists() {
	}

	/**
	 * Iterate through the enum, find all constants and map them into a choice list.
	 *
	 * @param clazz the enum
	 * @return the enum constants as a choice list
	 * @throws ReflectiveOperationException reflection-related method
	 */
	private static Map<String, Object> iterate(Class<?> clazz)
			throws ReflectiveOperationException {
		if (!clazz.isEnum())
			throw new CommandException(clazz + " is not an enum, so cannot be used as a ChoiceList");

		return Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(Object::toString, o -> o));
	}

	/**
	 * Get a choice list from an enum.
	 *
	 * @param clazz the enum
	 * @return the choice list
	 * @throws ReflectiveOperationException reflection-related method
	 */
	static Map<String, Object> getFromEnum(Class<?> clazz) throws ReflectiveOperationException {
		var map = ENUMS.get(clazz);
		if (map == null) {
			map = iterate(clazz);
			ENUMS.put(clazz, map);
		}

		return map;
	}
}
