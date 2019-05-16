package com.xuranus.core.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


/***
 *@author Johnny Zou
 *@link kunlun.zou@inveno.cn
 *@date 2015年8月8日
 *@Description: SharedPreference操作类
 *
 * 2017/6/22 强制把commit换成apply。
 **/

public class SharedPreferenceHelp {
	/**
	 *
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 * @return
	 */
	public static int getIntFromSharedPreference(Context mContext,
			String tableName, String key) {
		return getIntFromSharedPreference(mContext,tableName,key,0);
	}

	public static int getIntFromSharedPreference(Context mContext,
												 String tableName, String key , int defValue) {
		int value = defValue;

		if (null == mContext || null == key || key.length() < 1) {
			return value;
		}

		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		value = shared.getInt(key, defValue);
		return value;
	}

	/**
	 *
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 * @return
	 */
	public static boolean getBooleanFromSharedPreference(Context mContext,
														 String tableName, String key) {
		return getBooleanFromSharedPreference(mContext, tableName, key, false);
	}

	/**
	 *
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 * @return
	 */
	public static boolean getBooleanFromSharedPreference(Context mContext,
			String tableName, String key, boolean defaultValue) {
		boolean value = defaultValue;
		
		if (null == mContext || null == key || key.length() < 1) {
			return value;
		}
		
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		value = shared.getBoolean(key, value);
		return value;
	}

	/**
	 *
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 * @return
	 */
	public static float getFloatFromSharedPreference(Context mContext,
			String tableName, String key) {
		Float value = 0f;
		
		if (null == mContext || null == key || key.length() < 1) {
			return value;
		}
		
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		value = shared.getFloat(key, 0f);
		return value;
	}

	/**
	 *
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 * @return
	 */
	public static long getLongFromSharedPreference(Context mContext,
			String tableName, String key) {
		long value = 0;
		
		if (null == mContext || null == key || key.length() < 1) {
			return value;
		}
		
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		value = shared.getLong(key, 0);
		return value;
	}

	public static long getLongFromSharedPreference(Context mContext,
			String tableName, String key, long defaultValue) {
		long value = defaultValue;

		if (null == mContext || null == key || key.length() < 1) {
			return value;
		}

		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		value = shared.getLong(key, defaultValue);
		return value;
	}

	/**
	 * 获取String
	 * @param mContext 上下文
	 * @param tableName 表名
	 * @param key 键名
	 * @return 查到的值或""
	 */
	public static String getStringFromSharedPreference(Context mContext,
			String tableName, String key) {
		return getStringFromSharedPreference(mContext, tableName, key, "");
	}

	/**
	 * 获取String
	 * @param mContext 上下文
	 * @param tableName 表名
	 * @param key 键名
	 * @param defaultValue 默认值
	 * @return 查到的值或默认值
	 */
	public static String getStringFromSharedPreference(Context mContext,
			String tableName, String key, String defaultValue) {
		String value = defaultValue;

		if (null == mContext || null == key || key.length() < 1) {
			return value;
		}

		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		value = shared.getString(key, defaultValue);
		return value;
	}

	public static Set<String> getStringSet(Context context, String tableName, String key, Set<String> defValues) {
		if (context == null || TextUtils.isEmpty(tableName) || TextUtils.isEmpty(key)) {
			return defValues;
		}

		SharedPreferences preferences = context.getSharedPreferences(tableName, Context.MODE_PRIVATE);
		return preferences.getStringSet(key, defValues);
	}

	public static Map<String, ?> getAllFromSharedPreference(Context mContext, String tableName) {

		Map<String, ?> value = null;
		try{
			if (null == mContext) {
				return null;
			}

			SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
			value = shared.getAll();
		}catch (Exception e){
			e.printStackTrace();
		}

		return value;
	}

	/**
	 * commit()：线程安全，性能慢，一般来说在当前线程完成写文件操作
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 * @param value 存储的值 （boolean类型）
	 */
	public static void saveBooleanToSharedPreference(Context mContext,
			String tableName, String key, boolean value) {
		if (null == mContext || null == key || key.length() < 1) {
			return;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		Editor editor = shared.edit();
		editor.putBoolean(key, value);
		editor.apply();
	}

	/**
	 * commit()：线程安全，性能慢，一般来说在当前线程完成写文件操作
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 * @param value 存储的值 （int类型）
	 */
	public static void saveIntToSharedPreference(Context mContext,
			String tableName, String key, int value) {
		if (null == mContext || null == key || key.length() < 1) {
			return;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		Editor editor = shared.edit();
		editor.putInt(key, value);
		editor.apply();
	}

	/**
	 * commit()：线程安全，性能慢，一般来说在当前线程完成写文件操作
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 * @param value 存储的值 （float类型）
	 */
	public static void saveFloatToSharedPreference(Context mContext,
			String tableName, String key, float value) {
		if (null == mContext || null == key || key.length() < 1) {
			return;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		Editor editor = shared.edit();
		editor.putFloat(key, value);
		editor.apply();
	}

	/**
	 * commit()：线程安全，性能慢，一般来说在当前线程完成写文件操作
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 * @param value 存储的值 （long类型）
	 */
	public static void saveLongToSharedPreference(Context mContext,
			String tableName, String key, long value) {
		if (null == mContext || null == key || key.length() < 1) {
			return;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		Editor editor = shared.edit();
		editor.putLong(key, value);
		editor.apply();
	}

	/**
	 * commit()：线程安全，性能慢，一般来说在当前线程完成写文件操作
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 * @param value 存储的值 （String类型）
	 */
	public static void saveStringToSharedPreference(Context mContext,
			String tableName, String key, String value) {
		if (null == mContext || null == key || key.length() < 1) {
			return;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		Editor editor = shared.edit();
		editor.putString(key, value);
		editor.apply();
	}

	/**
	 * commit()：线程安全，性能慢，一般来说在当前线程完成写文件操作
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 */
	public static void removeValueToSharedPreference(Context mContext,
			String tableName, String key) {
		if (null == mContext || null == key || key.length() < 1) {
			return;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		Editor editor = shared.edit();
		editor.remove(key);
		editor.apply();
	}

	public static void removeValueToSharedPreference(Context mContext,
													 String tableName, ArrayList<String> keys) {
		if (null == mContext || null == keys || keys.size() < 1) {
			return;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		Editor editor = shared.edit();
		for (String key : keys){
			if(null == key || key.length() <1){
				continue;
			}
			editor.remove(key);
		}
		editor.apply();
	}

	public static void clearValueToSharedPreference(Context mContext, String tableName) {
		if (null == mContext) {
			return;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		Editor editor = shared.edit();
		editor.clear();
		editor.apply();
	}

	/**
	 * 判断是否存在key值
	 * @param mContext context
	 * @param key 键值名
	 */
	public static boolean containsValue(Context mContext, String tableName, String key) {
		if (null == mContext || null == key || key.length() < 1) {
			return false;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		return shared.contains(key);
	}

	/**
	 * apply()：线程不安全，性能高，异步处理IO操作，一定会把这个写文件操作放入一个SingleThreadExecutor线程池中处理
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 * @param value 存储的值 （boolean类型）
	 */
	public static void saveBooleanToSharedPreferenceApply(Context mContext,
													 String tableName, String key, boolean value) {
		if (null == mContext || null == key || key.length() < 1) {
			return;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		Editor editor = shared.edit();
		editor.putBoolean(key, value);
		editor.apply();
	}

	/**
	 * apply()：线程不安全，性能高，异步处理IO操作，一定会把这个写文件操作放入一个SingleThreadExecutor线程池中处理
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 * @param value 存储的值 （int 类型）
	 */
	public static void saveIntToSharedPreferenceApply(Context mContext,
												 String tableName, String key, int value) {
		if (null == mContext || null == key || key.length() < 1) {
			return;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		Editor editor = shared.edit();
		editor.putInt(key, value);
		editor.apply();
	}

	/**
	 * apply()：线程不安全，性能高，异步处理IO操作，一定会把这个写文件操作放入一个SingleThreadExecutor线程池中处理
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 * @param value 存储的值 （float 类型）
	 */
	public static void saveFloatToSharedPreferenceApply(Context mContext,
												   String tableName, String key, float value) {
		if (null == mContext || null == key || key.length() < 1) {
			return;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		Editor editor = shared.edit();
		editor.putFloat(key, value);
		editor.apply();
	}

	/**
	 * apply()：线程不安全，性能高，异步处理IO操作，一定会把这个写文件操作放入一个SingleThreadExecutor线程池中处理
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 * @param value 存储的值 （long 类型）
	 */
	public static void saveLongToSharedPreferenceApply(Context mContext,
												  String tableName, String key, long value) {
		if (null == mContext || null == key || key.length() < 1) {
			return;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		Editor editor = shared.edit();
		editor.putLong(key, value);
		editor.apply();
	}

	/**
	 * apply()：线程不安全，性能高，异步处理IO操作，一定会把这个写文件操作放入一个SingleThreadExecutor线程池中处理
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 * @param value 存储的值 （String 类型）
	 */
	public static void saveStringToSharedPreferenceApply(Context mContext,
													String tableName, String key, String value) {
		if (null == mContext || null == key || key.length() < 1) {
			return;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		Editor editor = shared.edit();
		editor.putString(key, value);
		editor.apply();
	}

	public static void applyStringSet(Context context, String tableName, String key, Set<String> values) {
		if (context == null || TextUtils.isEmpty(tableName) || TextUtils.isEmpty(key)) {
			return;
		}

		SharedPreferences preferences = context.getSharedPreferences(tableName, Context.MODE_PRIVATE);
		preferences.edit().putStringSet(key, values).apply();
	}

	/**
	 * apply()：线程不安全，性能高，异步处理IO操作，一定会把这个写文件操作放入一个SingleThreadExecutor线程池中处理
	 * @param mContext
	 * @param tableName 表名
	 * @param key 键值名
	 */
	public static void removeValueToSharedPreferenceApply(Context mContext,
													 String tableName, String key) {
		if (null == mContext || null == key || key.length() < 1) {
			return;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		Editor editor = shared.edit();
		editor.remove(key);
		editor.apply();
	}


	public static void clearToSharedPreferenceApply(Context mContext, String tableName) {
		if (null == mContext ) {
			return;
		}
		SharedPreferences shared = mContext.getSharedPreferences(tableName, 0);
		Editor editor = shared.edit();
		editor.clear();
		editor.apply();
	}
}
