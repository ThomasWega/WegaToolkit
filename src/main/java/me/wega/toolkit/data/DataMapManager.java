package me.wega.toolkit.data;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public abstract class DataMapManager<K, V> {
    private Map<K, V> dataMap;

    public DataMapManager() {
        this.dataMap = new HashMap<>();
    }

    @SneakyThrows
    public DataMapManager(@NotNull Class<? extends Map<K, V>> mapType) {
        this();
        this.dataMap = mapType.getConstructor((Class<?>) null).newInstance();
    }

    /**
     * Add a key-value pair to the data map.
     *
     * @param key   Key of type
     * @param value Value of type
     * @return True if the key-value pair was added, false if the key already exists and needed to be overwritten.
     */
    public boolean add(@NotNull K key, @NotNull V value) {
        return dataMap.put(key, value) == null;
    }

    /**
     * Add multiple key-value pairs to the data map.
     * @param data The data to add.
     * @return A map of values that were overwritten.
     */
    public @NotNull Map<K, V> addAll(@NotNull Map<K, V> data) {
        Map<K, V> unadded = new HashMap<>();
        for (Map.Entry<K, V> entry : data.entrySet()) {
            V oldValue = dataMap.put(entry.getKey(), entry.getValue());
            if (oldValue != null)
                unadded.put(entry.getKey(), oldValue);
        }
        return unadded;
    }

    public boolean has(@NotNull K key) {
        return dataMap.containsKey(key);
    }

    public boolean remove(@NotNull K key) {
        return dataMap.remove(key) != null;
    }

    public @Nullable V get(@NotNull K key) {
        return dataMap.get(key);
    }

    public @NotNull Set<K> getKeys() {
        return dataMap.keySet();
    }

    public @NotNull List<V> getValues() {
        return new ArrayList<>(dataMap.values());
    }

    public @NotNull Map<K, V> getMap() {
        return new HashMap<>(dataMap);
    }

    public void clear() {
        dataMap.clear();
    }

    /**
     * Retrieves keys with duplicate values in the data map.
     *
     * @return A list of duplicate keys.
     */
    public @NotNull Set<K> findDuplicateValues() {
        Set<V> uniqueValues = new HashSet<>();
        return dataMap.entrySet().stream()
                .filter(e -> !uniqueValues.add(e.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public void set(@NotNull Map<K, V> data) {
        dataMap.clear();
        dataMap.putAll(data);
    }
}
