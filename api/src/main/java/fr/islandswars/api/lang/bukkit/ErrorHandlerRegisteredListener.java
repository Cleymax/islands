package fr.islandswars.api.lang.bukkit;

import fr.islandswars.api.utils.ErrorHandler;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.plugin.RegisteredListener;

/**
 * File <b>ErrorHandlerRegisteredListener</b> located on fr.islandswars.api.lang.bukkit
 * ErrorHandlerRegisteredListener is a part of Islands Wars - Api.
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
 * @author DeltaEvo
 * @author Valentin Burgaud (Xharos), {@literal <xharos@islandswars.fr>}
 * Created the 10/04/2018 at 16:46
 * @since 0.2.9
 */
public class ErrorHandlerRegisteredListener extends RegisteredListener {

	private final RegisteredListener owner;
	private final ErrorHandler       handler;

	public ErrorHandlerRegisteredListener(RegisteredListener owner, ErrorHandler handler) {
		super(owner.getListener(), null, owner.getPriority(), owner.getPlugin(), owner.isIgnoringCancelled());
		this.owner = owner;
		this.handler = handler;
	}

	@Override
	public void callEvent(Event event) throws EventException {
		try {
			owner.callEvent(event);
		} catch (EventException e) {
			handler.handle(e.getCause());
		} catch (Throwable t) {
			handler.handle(t);
		}
	}
}
