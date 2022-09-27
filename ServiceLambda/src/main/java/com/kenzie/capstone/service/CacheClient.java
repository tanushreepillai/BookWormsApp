package com.kenzie.capstone.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.inject.Inject;
import java.util.Optional;

public class CacheClient {
    private final JedisPool pool;

    /**
     * Constructor for CacheClient.
     * @param pool a JedisPool instance provided by provideJedisPool()
     */
    @Inject
    public CacheClient(JedisPool pool) {
        this.pool = pool;
    }

    /**
     * Method that sets a key-value pair in the cache.
     * @param key     String used to identify an item in the cache
     * @param seconds The number of seconds during which the item is available
     * @param value   String representing the value set in the cache
     */
    public void setValue(String key, int seconds, String value) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null!");
        }

        try (Jedis jedis = pool.getResource()) {
            jedis.setex(key, seconds, value);
        }

    }

    /**
     * Method that retrieves a value from the cache.
     * @param key String used to identify the item being retrieved
     * @return String representing the value stored in the cache or an empty Optional in the case of a cache miss.
     */
    public Optional<String> getValue(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null!");
        }

        try (Jedis jedis = pool.getResource()) {
            return Optional.ofNullable(jedis.get(key));
        }

    }

    /**
     * Method to invalidate an item in the cache. Checks whether the requested key exists before invalidating.
     * @param key String representing the key to be deleted from the cache
     * @return true on invalidation, false if key does not exist in cache
     */
    public boolean invalidate(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null!");
        }

        try (Jedis jedis = pool.getResource()) {
            Long cacheInvalidated = jedis.del(key);
            return cacheInvalidated > 0;
        }
    }
}
