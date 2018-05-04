package fr.islandswars.api.infra;

import fr.islandswars.api.infra.jedis.RedisService;
import fr.islandswars.api.infra.rmq.RabbitMQService;

/**
 * File <b>ServiceManager</b> located on fr.islandswars.api.infra
 * ServiceManager is a part of Islands Wars - Api.
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
 * Created the 05/01/2018 at 10:30
 * @since 0.2.5
 * <p>
 * Get access to service
 */
public interface ServiceManager {

	/**
	 * @return the rabbitmq service to send message to specific queue/topic
	 */
	RabbitMQService getRabbitService();

	/**
	 * @return the redis service to simply get and set basic data
	 */
	RedisService getRedisService();

}
