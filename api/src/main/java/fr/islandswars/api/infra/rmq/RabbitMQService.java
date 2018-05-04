package fr.islandswars.api.infra.rmq;

/**
 * File <b>RabbitMQService</b> located on fr.islandswars.api.infra.rmq
 * RabbitMQService is a part of Islands Wars - Api.
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
 * Send message through rabbitmq
 */
public interface RabbitMQService {

	/**
	 * Proceed a topic publish with a given routingKey.
	 *
	 * @param buffer     a message to send
	 * @param topicName  the topic name to send message to
	 * @param routingKey a specific routing regex (canno't be null or empty)
	 * @throws Exception which is automatically logged
	 */
	void publishToATopic(StringBuffer buffer, String topicName, String routingKey) throws Exception;

	/**
	 * Proceed a basic publish to a known channel, this queue
	 * will be deleted if nobody use it.
	 *
	 * @param buffer    a message to send
	 * @param queueName a specific queue to send message to
	 * @throws Exception which is automatically logged
	 */
	void publishToAnOpenQueue(StringBuffer buffer, String queueName) throws Exception;

}
