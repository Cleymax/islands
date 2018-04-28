package fr.islandswars.api.log.internal;

import com.google.gson.annotations.SerializedName;

import java.util.logging.Level;

/**
 * File <b>ErrorLog</b> located on fr.islandswars.api.log.internal
 * ErrorLog is a part of Islands Wars - Api.
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
 * Created the 13/03/2018 at 13:46
 * @since 0.2.9
 */
public class ErrorLog extends DefaultLog {

    @SerializedName("stack-trace")
    private StackTraceElement[] stackTrace;

    public ErrorLog(Level level, String msg) {
        super(level, msg);
    }

    public ErrorLog supplyStacktrace(Throwable throwable) {
        this.stackTrace = throwable.getStackTrace();
        return this;
    }
}
