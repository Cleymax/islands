package fr.islandswars.api.infra.jedis;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * File <b>RedisService</b> located on fr.islandswars.api.infra.jedis
 * RedisService is a part of Islands Wars - Api.
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
 * Created the 05/01/2018 at 10:42
 * @since 0.2.5
 * <p>
 * Get and set String through redis
 */
public interface RedisService {

	/**
	 * Get the value of the specified key. If the key does not exist null is returned. If the value
	 * stored at key is not a string an error is returned because GET can only handle string values.
	 * <p>
	 * Time complexity: O(1)
	 *
	 * @param key a registered key to get
	 * @return wrapped bulk reply
	 */
	Optional<String> get(final String key);

	/**
	 * Return all the members (elements) of the set value stored at key. This is just syntax glue for
	 * {@link #sinter(String...) SINTER}.
	 * <p>
	 * Time complexity O(N)
	 *
	 * @param key a registered key to get
	 * @return wrapped multi bulk reply
	 */
	Optional<Set<String>> smembers(final String key);

	/**
	 * Return the members of a set resulting from the intersection of all the sets hold at the
	 * specified keys. Like in {@link #lrange(String, long, long) LRANGE} the result is sent to the
	 * client as a multi-bulk reply (see the protocol specification for more information). If just a
	 * single key is specified, then this command produces the same result as
	 * {@link #smembers(String) SMEMBERS}. Actually SMEMBERS is just syntax sugar for SINTER.
	 * <p>
	 * Non existing keys are considered like empty sets, so if one of the keys is missing an empty set
	 * is returned (since the intersection with an empty set always is an empty set).
	 * <p>
	 * Time complexity O(N*M) worst case where N is the cardinality of the smallest set and M the
	 * number of sets
	 *
	 * @param keys a registered key to get
	 * @return Multi bulk reply, specifically the list of common elements.
	 */
	Optional<Set<String>> sinter(final String... keys);

	/**
	 * Return the specified elements of the list stored at the specified key. Start and end are
	 * zero-based indexes. 0 is the first element of the list (the list head), 1 the next element and
	 * so on.
	 * <p>
	 * For example LRANGE foobar 0 2 will return the first three elements of the list.
	 * <p>
	 * start and end can also be negative numbers indicating offsets from the end of the list. For
	 * example -1 is the last element of the list, -2 the penultimate element and so on.
	 * <p>
	 * <b>Consistency with range functions in various programming languages</b>
	 * <p>
	 * Note that if you have a list of numbers from 0 to 100, LRANGE 0 10 will return 11 elements,
	 * that is, rightmost item is included. This may or may not be consistent with behavior of
	 * range-related functions in your programming language of choice (think Ruby's Range.new,
	 * Array#slice or Python's range() function).
	 * <p>
	 * LRANGE behavior is consistent with one of Tcl.
	 * <p>
	 * <b>Out-of-range indexes</b>
	 * <p>
	 * Indexes out of range will not produce an error: if start is over the end of the list, or start
	 * &gt; end, an empty list is returned. If end is over the end of the list Redis will threat it
	 * just like the last element of the list.
	 * <p>
	 * Time complexity: O(start+n) (with n being the length of the range and start being the start
	 * offset)
	 *
	 * @param key   a registered key to gt
	 * @param start start index
	 * @param stop  stop index
	 * @return wrapped multi bulk reply, specifically a list of elements in the specified range.
	 */
	Optional<List<String>> lrange(final String key, final long start, final long stop);

	/**
	 * Set the string value as value of the key. The string can't be longer than 1073741824 bytes (1
	 * GB).
	 * <p>
	 * Time complexity: O(1)
	 *
	 * @param key   key storage system
	 * @param value associated value
	 * @return status code reply
	 */
	String set(final String key, final String value);

	/**
	 * Add the string value to the head (LPUSH) or tail (RPUSH) of the list stored at key. If the key
	 * does not exist an empty list is created just before the append operation. If the key exists but
	 * is not a List an error is returned.
	 * <p>
	 * Time complexity: O(1)
	 *
	 * @param key     a key to register
	 * @param strings associated values
	 * @return Integer reply, specifically, the number of elements inside the list after the push
	 * operation.
	 */
	Long rpush(final String key, final String... strings);

	/**
	 * Add the string value to the head (LPUSH) or tail (RPUSH) of the list stored at key. If the key
	 * does not exist an empty list is created just before the append operation. If the key exists but
	 * is not a List an error is returned.
	 * <p>
	 * Time complexity: O(1)
	 *
	 * @param key     a key to register
	 * @param strings associated values
	 * @return Integer reply, specifically, the number of elements inside the list after the push
	 * operation.
	 */
	Long lpush(final String key, final String... strings);
}
