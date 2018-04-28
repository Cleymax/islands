package fr.islandswars.test;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.module.Module;

/**
 * File <b>GenericTest</b> located on fr.islandswars.test
 * GenericTest is a part of Islands Wars - Api.
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
 * Created the 03/01/2018 at 15:29
 */
public class GenericTest {

    public void test() {
        Core core = new Core();
        SomeModule mod = core.registerModule(SomeModule.class);
    }

    private class SomeModule extends Module {

        public SomeModule(IslandsApi api) {
            super(api);
        }

        @Override
        public void onLoad() {

        }

        @Override
        public void onDisable() {

        }

        @Override
        public void onEnable() {

        }
    }

    private class Core {

        private <T extends Module> T registerModule(Class<T> module) {
            try {
                T mod = module.getDeclaredConstructor(IslandsApi.class).newInstance(IslandsApi.getInstance());
                mod.onLoad();
                mod.onEnable();
                return mod;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
