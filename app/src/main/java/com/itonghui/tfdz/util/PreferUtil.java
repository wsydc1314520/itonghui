package com.itonghui.tfdz.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 本地存储工具类
 * @author demoxinyu
 * 
 */
public class PreferUtil {

	public static String getPrefString(Context context, String key,
                                       final String defaultValue) {
		final SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
		return settings.getString(key, defaultValue);
	}

	public static void setPrefString(Context context, final String key,
                                     final String value) {
		final SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
		settings.edit().putString(key, value).commit();
	}
	

	public static boolean getPrefBoolean(Context context, final String key,
                                         final boolean defaultValue) {
		final SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
		return settings.getBoolean(key, defaultValue);
	}

	public static void setPrefBoolean(Context context, final String key,
                                      final boolean value) {
		final SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
		settings.edit().putBoolean(key, value).commit();
	}

	public static void setPrefInt(Context context, final String key,
                                  final int value) {
		final SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
		settings.edit().putInt(key, value).commit();
	}

	public static int getPrefInt(Context context, final String key,
                                 final int defaultValue) {
		final SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
		return settings.getInt(key, defaultValue);
	}

	public static void setPrefFloat(Context context, final String key,
                                    final float value) {
		final SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
		settings.edit().putFloat(key, value).commit();
	}

	public static float getPrefFloat(Context context, final String key,
                                     final float defaultValue) {
		final SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
		return settings.getFloat(key, defaultValue);
	}

	public static void setPreLong(Context context, final String key,
                                  final long value) {
		final SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
		settings.edit().putLong(key, value).commit();
	}

	public static long getPrefLong(Context context, final String key,
                                   final long defaultValue) {
		final SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
		return settings.getLong(key, defaultValue);
	}
	
	
    public static <T> void setPreList(Context context, final String key, List<T> datalist) {
        final SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
        if (null == datalist) {
        	 return;  
        }
        Gson gson = new Gson();  
        String strJson = gson.toJson(datalist);
        settings.edit().putString(key, strJson).commit();  
    }  
  
    public static <T> List<T> getPreList(Context context, String key, Type listtype) {
		final SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
    	List<T> datalist=new ArrayList<T>();
        String strJson = settings.getString(key, null);
        if (null == strJson) {  
            return datalist;  
        }  
        Gson gson = new Gson();  
        datalist = gson.fromJson(strJson, listtype);  
        return datalist;  
    }  

	public static void clearPreference(Context context,
			final SharedPreferences p) {
		final Editor editor = p.edit();
		editor.clear();
		editor.commit();
	}

}
