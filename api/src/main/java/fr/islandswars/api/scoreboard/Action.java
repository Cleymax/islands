package fr.islandswars.api.scoreboard;

import java.util.Arrays;
import javax.annotation.Nullable;

/**
 * File <b>Action</b> located on fr.islandswars.api.scoreboard
 * Action is a part of Islands Wars - Api.
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
 * Created the 10/01/2018 at 17:54
 * @since 0.2.8.1
 */
public enum Action {

	CREATE(0),
	REMOVE(1),
	UPDATE(2),
	ADD_PLAYER(3),
	REMOVE_PLAYER(4);

	private final int mode;

	Action(final int mode) {
		this.mode = mode;
	}

	@Nullable
	public static Action getAction(int mode) {
		return Arrays.stream(values()).filter(action -> action.mode == mode).findFirst().orElse(null);
	}

	public int getMode() {
		return mode;
	}
}
