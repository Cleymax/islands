package fr.islandswars.core.bukkit.task;

import fr.islandswars.api.IslandsApi;
import fr.islandswars.api.task.TaskType;
import fr.islandswars.api.task.TimeType;
import fr.islandswars.api.task.Updater;
import fr.islandswars.api.task.UpdaterManager;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

/**
 * File <b>TaskManager</b> located on fr.islandswars.core.bukkit.task
 * TaskManager is a part of Islands Wars - Api.
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
 * Created the 23/04/2018 at 16:01
 * @since 0.2.9
 * <p>
 * TODO add task error handler
 */
public class TaskManager implements UpdaterManager {


	private final IslandsApi                     plugin;
	private final ConcurrentMap<Method, Integer> runningTasks;

	public TaskManager() {
		this.plugin = IslandsApi.getInstance();
		this.runningTasks = new ConcurrentHashMap<>();
	}

	@Override
	public void register(Object updatable) {
		Class updatableClass = updatable.getClass();
		for (Method method : updatableClass.getDeclaredMethods()) {
			if (method.isAnnotationPresent(Updater.class) & method.getParameterCount() == 0) {
				Updater updater = method.getAnnotation(Updater.class);
				method.setAccessible(true);
				TaskType type = updater.type();
				switch (type) {
					case ASYNC:
						if (isScheduled(updater)) {
							BukkitTask task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
								try {
									method.invoke(updatable);
								} catch (Exception e) {
									plugin.getInfraLogger().logError(e);
								}
							}, updater.delayed(), getDelta(updater));
							runningTasks.put(method, task.getTaskId());
						} else
							Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
								try {
									method.invoke(updatable);
								} catch (Exception e) {
									plugin.getInfraLogger().logError(e);
								}
							}, updater.delayed());
						break;
					case SYNC:
						if (isScheduled(updater)) {
							BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
								try {
									method.invoke(updatable);
								} catch (Exception e) {
									plugin.getInfraLogger().logError(e);
								}
							}, updater.delayed(), getDelta(updater));
							runningTasks.put(method, task.getTaskId());
						} else
							Bukkit.getScheduler().runTaskLater(plugin, () -> {
								try {
									method.invoke(updatable);
								} catch (Exception e) {
									plugin.getInfraLogger().logError(e);
								}
							}, updater.delayed());

						break;
				}
			}
		}
	}

	@Override
	public void stop(Object updatable) {
		Class updatableClass = updatable.getClass();
		for (Method method : updatableClass.getDeclaredMethods()) {
			if (method.isAnnotationPresent(Updater.class) & method.getParameterCount() == 0 & runningTasks.containsKey(method)) {
				Bukkit.getScheduler().cancelTask(runningTasks.remove(method)); //TODO instance
				method.setAccessible(false);
			}
		}
	}

	private long getDelta(Updater task) {
		return task.time() == TimeType.NONE ? task.delta() : task.time().getTimeInTick();
	}

	private boolean isScheduled(Updater task) {
		return task.time() != TimeType.NONE || task.delta() >= 1;
	}
}

