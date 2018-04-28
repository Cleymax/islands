package fr.islandswars.test.bukkit;

import fr.islandswars.api.task.TaskType;
import fr.islandswars.api.task.TimeType;
import fr.islandswars.api.task.Updater;

/**
 * File <b>TestTask</b> located on fr.islandswars.test.bukkit
 * TestTask is a part of Islands Wars - Api.
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
 * Created the 02/01/2018 at 17:25
 */
public class TestTask {

    @Updater(type = TaskType.SYNC)
    void runOnceSync() {
    }

    @Updater(type = TaskType.ASYNC)
    void runOnceAsync() {

    }

    @Updater(type = TaskType.ASYNC, delayed = 20 * 5)
    void runDelayedAsync() {

    }

    @Updater(type = TaskType.SYNC, delta = 1, time = TimeType.TICK)
    void runScheduleEachTickSync() {

    }

    @Updater(type = TaskType.ASYNC, delta = 1, time = TimeType.MINUT, delayed = 20 * 2)
    void runScheduledEachMinutAndDelayedAsync() {

    }
}
