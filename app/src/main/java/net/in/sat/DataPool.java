package net.in.sat;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rohitlondhe on 09/01/18.
 */

public class DataPool {

    private static DataPool instance = null;

    private Map<String, Object> pool = new HashMap<>();

    private DataPool() {
    }

    public static DataPool getInstance() {
        if (instance == null) {
            instance = new DataPool();
        }
        return instance;
    }


    public void put(String key, Object value) {
        pool.put(key, value);
    }

    public Object get(String key) {

        if (pool.isEmpty())
            return null;

        return pool.get(key);
    }

    public boolean remove(String key) {

        if (pool.containsKey(key)) {
            pool.remove(key);
            return true;
        }

        return false;
    }

}
