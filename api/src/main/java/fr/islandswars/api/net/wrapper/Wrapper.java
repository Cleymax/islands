package fr.islandswars.api.net.wrapper;

import fr.islandswars.api.utils.NMSReflectionUtil;

import java.lang.reflect.Field;

/**
 * File <b>Wrapper</b> located on fr.islandswars.api.net.wrapper
 * Wrapper is a part of Islands Wars - Api.
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
 * Created the 03/10/2017 at 13:13
 * @since 0.1
 * <p>
 * Reflective wrapper
 */
public abstract class Wrapper<T> {

    protected final T handle;

    public Wrapper(T handle) {
        this.handle = handle;
    }

    /**
     * Return the label of the given fieldname
     *
     * @param field a label to get from this field
     * @return an object, if founded
     * @see NMSReflectionUtil#getFieldAccessor(Field)
     */
    protected final Object getHandleValue(String field) {
        return NMSReflectionUtil.getFieldAccessor(handle.getClass(), field).get(handle);
    }

    /**
     * Set a new label to the given field
     *
     * @param field the field name to update
     * @param value the new label
     * @see NMSReflectionUtil#getFieldAccessor(Field)
     */
    protected final void setHandleValue(String field, Object value) {
        NMSReflectionUtil.getFieldAccessor(handle.getClass(), field).set(handle, value);
    }

    /**
     * Get the label from a given field
     *
     * @param field    the field name to get label from
     * @param instance the object to get field
     * @return the label if found
     * @see #getHandleValue(String, Object)
     */
    protected final Object getHandleValue(String field, Object instance) {
        return NMSReflectionUtil.getFieldAccessor(instance.getClass(), field).get(instance);
    }

    /**
     * Set a new label to the given field in the given instance
     *
     * @param field    the field name to update
     * @param instance an instance to set new label
     * @param value    the new label
     * @see NMSReflectionUtil#getFieldAccessor(Field)
     */
    protected final void setHandleValue(String field, Object instance, Object value) {
        NMSReflectionUtil.getFieldAccessor(instance.getClass(), field).set(instance, value);
    }

}
