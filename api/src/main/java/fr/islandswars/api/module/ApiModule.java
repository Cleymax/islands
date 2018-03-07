package fr.islandswars.api.module;

import fr.islandswars.api.bossbar.BarManager;
import fr.islandswars.api.module.bukkit.ArrowModule;

/**
 * File <b>ApiModule</b> located on fr.islandswars.api.module
 * ApiModule is a part of Islands Wars - Api.
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p>
 *
 * @author Valentin Burgaud (Xharos), {@literal <xharos@islandswars.fr>}
 * Created the 03/01/2018 at 15:35
 * @since 0.2.4.1
 * <p>
 * Represent an existing (and already instanciates sometimes) module
 */
public enum ApiModule {

	BOSS_BAR(BarManager.class),
	ARROW(ArrowModule.class);

	private final Class<? extends Module> moduleClass;

	ApiModule(Class<? extends Module> moduleClass) {
		this.moduleClass = moduleClass;
	}

	/**
	 * @return this module class
	 */
	public Class<? extends Module> getModuleClass() {
		return moduleClass;
	}
}
