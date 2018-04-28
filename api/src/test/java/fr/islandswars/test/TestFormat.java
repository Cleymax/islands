package fr.islandswars.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Supplier;

/**
 * File <b>TestFormat</b> located on fr.islandswars.test
 * TestFormat is a part of Islands Wars - Api.
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
 * @author xharos, {@literal <xharos@islandswars.fr>}
 * Created the 26/12/2017 at 17:49
 */
public class TestFormat {

    @Test
    public void testDecimalFormat() {
        Assert.assertEquals("hub-1", String.format("hub-%d", 1));
    }

    @Test
    public void someTest() {
        Supplier<Object[]> defaultParameters = () -> new Object[]{"test"};
        Assert.assertEquals("test", String.format("test", defaultParameters.get()));

        defaultParameters = () -> new Object[0];
        Assert.assertEquals("test", String.format("test", defaultParameters.get()));
    }
}
