package fr.islandswars.core.bukkit.command;

import fr.islandswars.api.cmd.CommandSerializer;
import org.apache.commons.lang.SerializationException;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * File <b>CommandSerializers</b> located on fr.islandswars.core.bukkit.command
 * CommandSerializers is a part of Islands Wars - Api.
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
 * @author SkybeastMC
 * Created the 20/03/2018 at 13:09
 * @since 0.2.9
 */
public class CommandSerializers {

    static final CommandSerializer<Boolean> BOOLEAN = new ConstantTabCompleteCommandSerializer<>(
            Boolean::valueOf,
            "boolean",
            Arrays.asList("false", "true")
    );
    static final CommandSerializer<Byte> BYTE = new NoTabCommandSerializer<>(Byte::valueOf, "byte");


    /* ----------------------------- */
    /* ---- DEFAULT SERIALIZERS ---- */
    /* ----------------------------- */

    // Primitives
    static final CommandSerializer<Short> SHORT = new NoTabCommandSerializer<>(Short::valueOf, "short");
    static final CommandSerializer<Integer> INTEGER = new NoTabCommandSerializer<>(Integer::valueOf, "int");
    static final CommandSerializer<Long> LONG = new NoTabCommandSerializer<>(Long::valueOf, "long");
    static final CommandSerializer<Float> FLOAT = new NoTabCommandSerializer<>(Float::valueOf, "float");
    static final CommandSerializer<Double> DOUBLE = new NoTabCommandSerializer<>(Double::valueOf, "double");
    static final CommandSerializer<Character> CHARACTER = new NoTabCommandSerializer<>(str -> str.charAt(0), "char");
    static final CommandSerializer<Player> PLAYER = new CommandSerializerImpl<>(
            CommandSerializers::getPlayer, "Player", () -> sort(getAllPlayers())
    );
    static final CommandSerializer<CommandSender> COMMAND_SENDER = new CommandSerializerImpl<>(
            str -> "@CONSOLE".equalsIgnoreCase(str) ? Bukkit.getConsoleSender() : getPlayer(str), "Command Sender", () -> sort(append(getAllPlayers(), "@CONSOLE"))
    );

    // Others
    /**
     * Used to cache all default serializers.
     */
    private static final Map<Class<?>, CommandSerializer<?>> INSTANCES = new HashMap<>();
    /**
     * Used to cache all custom serializers.
     */
    private static final Map<Class<?>, CommandSerializer<?>> CACHE = new HashMap<>();

    /*
     * Setup the INSTANCES field.
     */
    static {
        // Primitives
        INSTANCES.put(boolean.class, BOOLEAN);
        INSTANCES.put(Boolean.class, BOOLEAN);
        INSTANCES.put(byte.class, BYTE);
        INSTANCES.put(Byte.class, BYTE);
        INSTANCES.put(short.class, SHORT);
        INSTANCES.put(Short.class, SHORT);
        INSTANCES.put(int.class, INTEGER);
        INSTANCES.put(Integer.class, INTEGER);
        INSTANCES.put(long.class, LONG);
        INSTANCES.put(Long.class, LONG);
        INSTANCES.put(float.class, FLOAT);
        INSTANCES.put(Float.class, FLOAT);
        INSTANCES.put(double.class, DOUBLE);
        INSTANCES.put(Double.class, DOUBLE);
        INSTANCES.put(char.class, CHARACTER);
        INSTANCES.put(Character.class, CHARACTER);

        // Others
        INSTANCES.put(Player.class, PLAYER);
        INSTANCES.put(CommandSender.class, COMMAND_SENDER);
    }

    private CommandSerializers() {
    }

    /**
     * Append a value to a list.
     *
     * @param list the list
     * @param str  the value to append
     * @return the list
     */
    private static List<String> append(List<String> list, String str) {
        list.add(str);
        return list;
    }

    /* -------------------------- */
    /* ---- SERIALIZER UTILS ---- */
    /* -------------------------- */

    /**
     * Get all online players' name.
     *
     * @return all online players' name into a modifiable list
     */
    private static List<String> getAllPlayers() {
        return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
    }

    /**
     * Get a player from its name.
     *
     * @param str the name of the player
     * @return the player
     * @throws SerializationException if the player was not found
     */
    private static Player getPlayer(String str) throws SerializationException {
        Player player = Bukkit.getPlayerExact(str);
        if (player == null)
            throw new SerializationException("Cannot find player \"" + str + '"');
        return player;
    }

    /**
     * Format an argument.
     *
     * @param formatter the formatter
     * @param arg       the argument
     * @param valueType the value type of the argument
     * @param <T>       the type of argument
     * @return the formatted argument
     * @throws SerializationException if error while formatting
     */
    private static <T> T safeFormat(SafeFormatter<T> formatter, String arg, String valueType) throws SerializationException {
        try {
            return formatter.format(arg);
        } catch (NumberFormatException ignored) {
            throw new SerializationException("Input \"" + arg + "\" is not of type " + valueType);
        }
    }

    /**
     * Sort the list.
     *
     * @param list the list
     * @return the list
     */
    private static List<String> sort(List<String> list) {
        list.sort(null);
        return list;
    }

    /**
     * Get a default serializer from its serialized class.
     *
     * @param <T>   the type of the serializer
     * @param clazz the class
     * @return the serializer
     */
    @SuppressWarnings("unchecked")
    public static <T> CommandSerializer<T> getSerializer(Class<T> clazz) {
        return (CommandSerializer<T>) INSTANCES.get(clazz);
    }

    /**
     * Get a default serializer from its class and serialized class.
     *
     * @param clazz      the serialized class
     * @param serializer the serializer class
     * @param <T>        the type of the serialized class
     * @return the command serializer
     * @throws ReflectiveOperationException reflection-related method
     */
    @SuppressWarnings("unchecked")
    public static <T> CommandSerializer<T> serializerOf(Class<T> clazz, Class<? extends CommandSerializer> serializer) throws ReflectiveOperationException {
        CommandSerializer serial = CACHE.get(clazz);

        if (serial != null && serial.getClass() == serializer)
            return serial;

        serial = serializer.getConstructor().newInstance();
        CACHE.put(clazz, serial);
        return serial;
    }

    /**
     * Argument-formatter.
     *
     * @param <T> the type of argument
     */
    @FunctionalInterface
    private interface SafeFormatter<T> {

        T format(String arg) throws NumberFormatException, SerializationException;
    }

    /**
     * A serializer with no tab complete.
     *
     * @param <T> the type of argument
     */
    private static class NoTabCommandSerializer<T> implements CommandSerializer<T> {

        final SafeFormatter<T> formatter;
        final String valueType;

        NoTabCommandSerializer(SafeFormatter<T> formatter, String valueType) {
            this.formatter = formatter;
            this.valueType = valueType;
        }

        @Override
        public T serialize(String arg) throws SerializationException {
            return safeFormat(formatter, arg, valueType);
        }

        @Override
        public String valueType() {
            return valueType;
        }
    }

    /**
     * A serializer with tab completes.
     *
     * @param <T> the type of argument
     */
    private static class CommandSerializerImpl<T> extends NoTabCommandSerializer<T> {

        final Supplier<List<String>> tabCompleter;

        CommandSerializerImpl(SafeFormatter<T> formatter, String valueType, Supplier<List<String>> tabCompleter) {
            super(formatter, valueType);
            this.tabCompleter = tabCompleter;
        }

        @Override
        public List<String> getAllTabCompletes() {
            return tabCompleter.get();
        }

        @Override
        public T serialize(String arg) throws SerializationException {
            return formatter.format(arg);
        }
    }

    /**
     * A serializer with constant tab completes.
     *
     * @param <T> the type of argument
     */
    private static class ConstantTabCompleteCommandSerializer<T> extends NoTabCommandSerializer<T> {

        final List<String> tabCompletes;

        ConstantTabCompleteCommandSerializer(SafeFormatter<T> formatter, String valueType, List<String> tabCompletes) {
            super(formatter, valueType);
            this.tabCompletes = tabCompletes;
        }

        @Override
        public T serialize(String arg) throws SerializationException {
            return formatter.format(arg);
        }

        @Override
        public List<String> getAllTabCompletes() {
            return tabCompletes;
        }
    }
}
