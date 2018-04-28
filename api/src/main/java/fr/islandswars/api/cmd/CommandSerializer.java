package fr.islandswars.api.cmd;

import java.util.List;

/**
 * File <b>CommandSerializer</b> located on fr.islandswars.api.cmd
 * CommandSerializer is a part of Islands Wars - Api.
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
 * Created the 16/03/2018 at 23:15
 * @since 0.2.9
 * <p>
 * Command serializer to parse arguments
 */
public interface CommandSerializer<T> {

    /**
     * Get tab completes for this serializers.
     * <p>
     * Warning: These tab complete must be sorted.
     *
     * @return the tab completes for this serializers
     */
    default List<String> getAllTabCompletes() {
        return null;
    }

    /**
     * @param arg the argument to serialize
     * @return the serialized argument
     * @throws ReflectiveOperationException thrown if illegal syntax is found
     */
    T serialize(String arg) throws ReflectiveOperationException;

    /**
     * Get the label type of the serializer.
     *
     * @return the label type of the serializer
     */
    String valueType();
}
