package com.xuranus.core.storage;

import android.content.Context;

import java.util.Set;

/***
 *@author Johnny Zou
 *@link kunlun.zou@inveno.cn
 *@date 2015年8月8日
 *@Description: 统一轻量级数据存储，与用户有关的数据存储在不同的表中，无关的数据存储在同一表中，便于统一管理
 **/

public class SharedPreferenceStorage {

	/**
	 * 用于公共数据存储；与用户无关；
	 */
	private final static String COMMON_DATA = "common_data";

	/**
	 * 用于当前用户存储；
	 */
	private final static String CURRENT_USER = "current_user";

	/**
	 *
	 * @param mContext
	 * @param key 键值名
	 * @param defaultValue 默认值
	 * @return
	 */
	public static boolean getBooleanCommonPreference(Context mContext, String key, boolean defaultValue) {
		return SharedPreferenceHelp.getBooleanFromSharedPreference(mContext, COMMON_DATA, key, defaultValue);
	}

	/**
	 *
	 * @param mContext
	 * @param key 键值名
	 * @return
	 */
	public static boolean getBooleanCommonPreference(Context mContext, String key) {
		return SharedPreferenceHelp.getBooleanFromSharedPreference(mContext, COMMON_DATA, key);
	}

	/**
	 *
	 * @param mContext
	 * @param key 键值名
	 * @return
	 */
	public static int getIntCommonPreference(Context mContext, String key) {
		return SharedPreferenceHelp.getIntFromSharedPreference(mContext, COMMON_DATA, key);
	}

	/**
	 *
	 * @param mContext
	 * @param key
	 * @param defValue
     * @return
     */
	public static int getIntCommonPreference(Context mContext, String key, int defValue) {
		return SharedPreferenceHelp.getIntFromSharedPreference(mContext, COMMON_DATA, key,defValue);
	}

	/**
	 *
	 * @param mContext
	 * @param key 键值名
	 * @return
	 */
	public static float getFloatCommonPreference(Context mContext, String key) {
		return SharedPreferenceHelp.getFloatFromSharedPreference(mContext, COMMON_DATA, key);
	}

	/**
	 *
	 * @param mContext
	 * @param key 键值名
	 * @return
	 */
	public static long getLongCommonPreference(Context mContext, String key) {
		return SharedPreferenceHelp.getLongFromSharedPreference(mContext, COMMON_DATA, key);
	}

	/**
	 *
	 * @param mContext 上下文
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return 返回值
	 */
	public static long getLongCommonPreference(Context mContext, String key, long defaultValue) {
		return SharedPreferenceHelp.getLongFromSharedPreference(mContext, COMMON_DATA, key, defaultValue);
	}

	/**
	 *
	 * @param mContext
	 * @param key 键值名
	 * @return
	 */
	public static String getStringCommonPreference(Context mContext, String key) {
		return SharedPreferenceHelp.getStringFromSharedPreference(mContext, COMMON_DATA, key);
	}

	public static Set<String> getStringSet(Context context, String key, Set<String> defValues) {
		return SharedPreferenceHelp.getStringSet(context, COMMON_DATA, key, defValues);
	}

	/**
	 * commit()：线程安全，性能慢，一般来说在当前线程完成写文件操作
	 * @param mContext
	 * @param key 键值名
	 * @param value 存储的值 （boolean 类型）
	 */
	public static void saveBooleanCommonPreference(Context mContext, String key,
			boolean value) {
		SharedPreferenceHelp.saveBooleanToSharedPreference(mContext, COMMON_DATA, key, value);
	}

	/**
	 * commit()：线程安全，性能慢，一般来说在当前线程完成写文件操作
	 * @param mContext
	 * @param key 键值名
	 * @param value 存储的值 （int 类型）
	 */
	public static void saveIntCommonPreference(Context mContext, String key,
			int value) {
		SharedPreferenceHelp.saveIntToSharedPreference(mContext, COMMON_DATA, key, value);
	}

	/**
	 * commit()：线程安全，性能慢，一般来说在当前线程完成写文件操作
	 * @param mContext
	 * @param key 键值名
	 * @param value 存储的值 （float 类型）
	 */
	public static void saveFloatCommonPreference(Context mContext, String key,
			float value) {
		SharedPreferenceHelp.saveFloatToSharedPreference(mContext, COMMON_DATA, key, value);
	}

	/**
	 * commit()：线程安全，性能慢，一般来说在当前线程完成写文件操作
	 * @param mContext
	 * @param key 键值名
	 * @param value 存储的值 （long 类型）
	 */
	public static void saveLongCommonPreference(Context mContext, String key,
			long value) {
		SharedPreferenceHelp.saveLongToSharedPreference(mContext, COMMON_DATA, key, value);
	}

	/**
	 * commit()：线程安全，性能慢，一般来说在当前线程完成写文件操作
	 * @param mContext
	 * @param key 键值名
	 * @param value 存储的值 （String 类型）
	 */
	public static void saveStringCommonPreference(Context mContext, String key,
			String value) {
		SharedPreferenceHelp.saveStringToSharedPreference(mContext, COMMON_DATA, key, value);
	}

	/**
	 * commit()：线程安全，性能慢，一般来说在当前线程完成写文件操作
	 * @param mContext
	 * @param key 键值名
	 */
	public static void removeCommonPreferenceDate(Context mContext, String key) {
		SharedPreferenceHelp.removeValueToSharedPreference(mContext, COMMON_DATA, key);
	}



	/**
	 * 判断是否存在key值
	 * @param mContext context
	 * @param key 键值名
	 */
	public static boolean containsValueInCommonPreference(Context mContext, String key){
		return SharedPreferenceHelp.containsValue(mContext, COMMON_DATA, key);
	}

	/**
	 * apply()：线程不安全，性能高，异步处理IO操作，一定会把这个写文件操作放入一个SingleThreadExecutor线程池中处理
	 * @param mContext
	 * @param key 键值名
	 * @param value 存储的值 （boolean 类型）
	 */
	public static void saveBooleanCommonPreferenceApply(Context mContext, String key,
												   boolean value) {
		SharedPreferenceHelp.saveBooleanToSharedPreferenceApply(mContext, COMMON_DATA, key, value);
	}

	/**
	 * apply()：线程不安全，性能高，异步处理IO操作，一定会把这个写文件操作放入一个SingleThreadExecutor线程池中处理
	 * @param mContext
	 * @param key 键值名
	 * @param value 存储的值 （int 类型）
	 */
	public static void saveIntCommonPreferenceApply(Context mContext, String key,
											   int value) {
		SharedPreferenceHelp.saveIntToSharedPreferenceApply(mContext, COMMON_DATA, key, value);
	}

	/**
	 * apply()：线程不安全，性能高，异步处理IO操作，一定会把这个写文件操作放入一个SingleThreadExecutor线程池中处理
	 * @param mContext
	 * @param key 键值名
	 * @param value 存储的值 （float 类型）
	 */
	public static void saveFloatCommonPreferenceApply(Context mContext, String key,
												 float value) {
		SharedPreferenceHelp.saveFloatToSharedPreferenceApply(mContext, COMMON_DATA, key, value);
	}

	/**
	 * apply()：线程不安全，性能高，异步处理IO操作，一定会把这个写文件操作放入一个SingleThreadExecutor线程池中处理
	 * @param mContext
	 * @param key 键值名
	 * @param value 存储的值 （long 类型）
	 */
	public static void saveLongCommonPreferenceApply(Context mContext, String key,
												long value) {
		SharedPreferenceHelp.saveLongToSharedPreferenceApply(mContext, COMMON_DATA, key, value);
	}

	/**
	 * apply()：线程不安全，性能高，异步处理IO操作，一定会把这个写文件操作放入一个SingleThreadExecutor线程池中处理
	 * @param mContext
	 * @param key 键值名
	 * @param value 存储的值 （String 类型）
	 */
	public static void saveStringCommonPreferenceApply(Context mContext, String key,
												  String value) {
		SharedPreferenceHelp.saveStringToSharedPreferenceApply(mContext, COMMON_DATA, key, value);
	}

	public static void applyStringSet(Context context, String key, Set<String> values) {
		SharedPreferenceHelp.applyStringSet(context, COMMON_DATA, key, values);
	}

	/**
	 * apply()：线程不安全，性能高，异步处理IO操作，一定会把这个写文件操作放入一个SingleThreadExecutor线程池中处理
	 * @param mContext
	 * @param key 键值名
	 */
	public static void removeCommonPreferenceDateApply(Context mContext, String key) {
		SharedPreferenceHelp.removeValueToSharedPreferenceApply(mContext, COMMON_DATA, key);
	}

	//TODO 与用户有关的数据存储在不同的表中的功能待续。。。
}
