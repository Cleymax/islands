package fr.islandswars.api.cmd.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * File <b>Compound</b> located on fr.islandswars.api.cmd.lang
 * Compound is a part of Islands Wars - Api.
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
 * @author Valentin Burgaud (Xharos), {@literal <xharos@islandswars.fr>}
 * Created the 16/03/2018 at 23:20
 * @since 0.2.9
 * <p>
 * Inner class or method compound
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Compound {

    /**
     * Specify aliases that can be duplicated with other compound that have the same length
     * /test compound and /test compound1
     * compound and compound1 can't have same aliases
     *
     * @return this specific command aliases
     * @throws IllegalArgumentException if one of the given alias is already registered
     */
    String[] aliases() default {};


    /**
     * By default, it will uses method's name as label, but you can
     * specify your own label here (it will be shown in description)
     *
     * @return this inner compound label
     */
    String label() default "";

    String description() default "";
}
