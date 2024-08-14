package com.nus.wewalk.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * 本地存储类
 */
public class XShareCacheUtils {

    private volatile static XShareCacheUtils mInstance = null;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public XShareCacheUtils() {
    }

    public static XShareCacheUtils getInstance() {
        if (mInstance == null) {
            synchronized (XShareCacheUtils.class) {
                if (mInstance == null) {
                    mInstance = new XShareCacheUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        init(context, context.getPackageName());
    }

    /**
     * 初始化
     * @param context
     * @param name
     */
    public void init(Context context, String name) {
        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    /**
     * 存储
     */
    public void put(String key, Object object) {
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.commit();
    }

    /**
     * 获取保存的数据
     */
    public Object get(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        } else {
            return sharedPreferences.getString(key, null);
        }
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public void putLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public String getString(String key, String defaultObject) {
        return sharedPreferences.getString(key, defaultObject);
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public int getInt(String key, int defaultObject) {
        return sharedPreferences.getInt(key, defaultObject);
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public boolean getBoolean(String key, boolean defaultObject) {
        return sharedPreferences.getBoolean(key, defaultObject);
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public long getLong(String key, long defaultObject) {
        return sharedPreferences.getLong(key, defaultObject);
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public float getFloat(String key, float defaultObject) {
        return sharedPreferences.getFloat(key, defaultObject);
    }

    public float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0f);
    }

    /**
     * 移除某个key值已经对应的值
     */
    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否存在
     */
    public Boolean contain(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }
}