package com.itonghui.tfdz.util;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 * @Description: 数学运算及相关共用方法
 * @author yandaocheng
 * @date 2018-04-23
 *
 */
public class MathExtend {
	// 默认除法运算精度
	private static final int DEFAULT_DIV_SCALE = 10;

	/**
	 * 提供精确的加法运算。
	 *
	 * @param v1
	 * @param v2
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的加法运算
	 *
	 * @param v1
	 * @param v2
	 * @return 两个参数数学加和，以字符串格式返回
	 */
	public static String add(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).toString();
	}

	/**
	 * 提供精确的减法运算。
	 *
	 * @param v1
	 * @param v2
	 * @return 两个参数的差
	 */
	public static double subtract(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算
	 *
	 * @param v1
	 * @param v2
	 * @return 两个参数数学差，以字符串格式返回
	 */
	public static String subtract(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).toString();
	}

	/**
	 * 提供精确的乘法运算。
	 *
	 * @param v1
	 * @param v2
	 * @return 两个参数的积
	 */
	public static double multiply(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算
	 *
	 * @param v1
	 * @param v2
	 * @return 两个参数的数学积，以字符串格式返回
	 */
	public static String multiply(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).toString();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
	 *
	 * @param v1
	 * @param v2
	 * @return 两个参数的商
	 */
	public static double divide(double v1, double v2) {
		return divide(v1, v2, DEFAULT_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
	 *
	 * @param v1
	 * @param v2
	 * @param scale
	 *            表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double divide(double v1, double v2, int scale) {
		return divide(v1, v2, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
	 *
	 * @param v1
	 * @param v2
	 * @param scale
	 *            表示需要精确到小数点以后几位
	 * @param round_mode
	 *            表示用户指定的舍入模式
	 * @return 两个参数的商
	 */
	public static double divide(double v1, double v2, int scale, int round_mode) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, round_mode).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
	 *
	 * @param v1
	 * @param v2
	 * @return 两个参数的商，以字符串格式返回
	 */
	public static String divide(String v1, String v2) {
		return divide(v1, v2, DEFAULT_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
	 *
	 * @param v1
	 * @param v2
	 * @param scale
	 *            表示需要精确到小数点以后几位
	 * @return 两个参数的商，以字符串格式返回
	 */
	public static String divide(String v1, String v2, int scale) {
		return divide(v1, v2, DEFAULT_DIV_SCALE, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
	 *
	 * @param v1
	 * @param v2
	 * @param scale
	 *            表示需要精确到小数点以后几位
	 * @param round_mode
	 *            表示用户指定的舍入模式
	 * @return 两个参数的商，以字符串格式返回
	 */
	public static String divide(String v1, String v2, int scale, int round_mode) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, round_mode).toString();
	}

	/**
	 * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
	 *
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		return round(v, scale, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 提供精确的小数位四舍五入处理
	 *
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @param round_mode
	 *            指定的舍入模式
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale, int round_mode) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		return b.setScale(scale, round_mode).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
	 *
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果，以字符串格式返回
	 */
	public static String round(String v, int scale) {
		if (v == null || v == "") {
			return "--";
		}
		return round(v, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 提供精确的小数位四舍五入处理
	 *
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @param round_mode
	 *            指定的舍入模式
	 * @return 四舍五入后的结果，以字符串格式返回
	 */
	public static String round(String v, int scale, int round_mode) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		return b.setScale(scale, round_mode).toString();
	}

	/*
	 * 判断字符是否为空，null
	 */
	public static String checknull(String s) {
		if (s == null || s == "") {
			return "--";
		}
		return s;
	}

	/*
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(String s) {
		if (s == null || s == "") {
			return "--";
		}
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//yyyy-MM-dd HH:mm:ss
		long ss = Long.valueOf(s);
		Date date = new Date(ss);
		res = simpleDateFormat.format(date);
		return res;
	}

	/*
	 * 将时间戳转换为时间(到当天时间截止)-------结束时间
	 */
	public static String stampToTodayEnd(Long s) {
		if (s == 0) {
			return "";
		}
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(s);
		res = simpleDateFormat.format(date) + " 23:59:59";
		return res;
	}

	/*
	 * 将时间戳转换为时间(当天开始时间)-------开始时间
	 */
	public static String stampToTodayStart(Long s) {
		if (s == 0) {
			return "";
		}
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(s);
		res = simpleDateFormat.format(date) + " 00:00:00";
		return res;
	}

	/*
	 * 将时间戳转换为时间（取整8位）
	 */
	public static String stampToDateEight(String s) {
		if (s == null || s == "") {
			return "";
		}
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		long ss = Long.valueOf(s);
		Date date = new Date(ss);
		res = simpleDateFormat.format(date);
		return res;
	}

	/*
	 * 截取时间字符串
	 */
	public static String subTime(String s) {
		if (s == null || s == "") {
			return "";
		}
		return s.substring(0, 10);
	}

	/*
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty()) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds)));
	}

	/*
	 * 将时间转换为时间戳
	 */
	public static String dateToStamp(String s) throws ParseException {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}

	/*
	 * 将时间转换为时间戳
	 */
	public static String dateToStamp(String s, String format) throws ParseException {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}

	/*
	 * 格式化时间
	 */
	public static String dateFormat(String s, String format) {
		SimpleDateFormat sdf = null;
		if (format == null) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else {
			sdf = new SimpleDateFormat(format);
		}
		Date date_util = null;
		try {
			date_util = sdf.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date date_sql = new java.sql.Date(date_util.getTime());// 转换为sql.date
		String date = sdf.format(date_sql);
		date = sdf.format(date_util);
		return date;
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getNowTime(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return simpleDateFormat.format(date);
	}
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 */
	public static int px2sp(Context context, float spValue) {
		final float scale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue / scale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 */
	public static int sp2px(Context context, float spValue) {
		final float scale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * scale + 0.5f);
	}

	/**
	 * 获取屏幕宽度
	 */
	@SuppressWarnings("deprecation")
	public static int getWindowWidth(Activity mActivity) {
		WindowManager windowManager = mActivity.getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		return display.getWidth();
	}

	/**
	 * 设置tablayout指示器的长短
	 */
	public static void showTabTextAdapteIndicator(final TabLayout tab, final Context context) {
		tab.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				tab.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				Class<?> tabLayout = tab.getClass();
				Field tabStrip = null;
				try {
					tabStrip = tabLayout.getDeclaredField("mTabStrip");
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				}
				tabStrip.setAccessible(true);
				LinearLayout ll_tab = null;
				try {
					ll_tab = (LinearLayout) tabStrip.get(tab);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				int maxLen = 0;
				int maxTextSize = 0;
				int tabCount = ll_tab.getChildCount();
				for (int i = 0; i < tabCount; i++) {
					View child = ll_tab.getChildAt(i);
					child.setPadding(0, 0, 0, 0);
					if (child instanceof ViewGroup) {
						ViewGroup viewGroup = (ViewGroup) child;
						for (int j = 0; j < ll_tab.getChildCount(); j++) {
							if (viewGroup.getChildAt(j) instanceof TextView) {
								TextView tabTextView = (TextView) viewGroup.getChildAt(j);
								int length = tabTextView.getText().length();
								maxTextSize = (int) tabTextView.getTextSize() > maxTextSize
										? (int) tabTextView.getTextSize() : maxTextSize;
								maxLen = length > maxLen ? length : maxLen;
							}
						}

					}
					int margin = (tab.getWidth() / tabCount - (maxTextSize + MathExtend.dpToPx(context, 2)) * maxLen)
							/ 2 - MathExtend.dpToPx(context, 2);
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
							LinearLayout.LayoutParams.MATCH_PARENT, 1);
					params.leftMargin = margin;
					params.rightMargin = margin;
					child.setLayoutParams(params);
					child.invalidate();
				}
			}
		});
	}

	public static int pxToDp(Context context, int px) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
		return dp;
	}

	public static int dpToPx(Context context, int dp) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
		return px;
	}

	/*
	 * 修改价格字体大小
	 */
	public static SpannableString modifyTextSize(String text) {
		if (text == "" || text == null)
			return null;
		String mCopPrice = "￥" + MathExtend.round(text, 2);
		int mIndex = mCopPrice.indexOf(".");
		SpannableString spanString = new SpannableString(mCopPrice);
		spanString.setSpan(new AbsoluteSizeSpan(20, true), 1, mIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		return spanString;
	}

	/*
	 * 修改状态栏颜色
	 */
	@SuppressLint("NewApi") @SuppressWarnings("deprecation")
	public static void setWindowStatusBarColor(Activity activity, int colorResId) {
		try {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				Window window = activity.getWindow();
				window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
				window.setStatusBarColor(activity.getResources().getColor(colorResId));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressLint("NewApi") @SuppressWarnings("deprecation")
	public static void setWindowStatusBarColor(Dialog dialog, int colorResId) {
		try {

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				Window window = dialog.getWindow();
				window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
				window.setStatusBarColor(dialog.getContext().getResources().getColor(colorResId));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 允许拨打电话权限
	 */
	private static String[] PERMISSIONS_STORAGE = { "android.permission.CALL_PHONE" };

	public static boolean getPermissions(Activity activity) {
		try {
			// 检测是否有权限
			int permission = ActivityCompat.checkSelfPermission(activity, "android.permission.CALL_PHONE");
			if (permission != PackageManager.PERMISSION_GRANTED) {
				// 没有权限，去申请权限，会弹出对话框
				ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, 1);
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * 允许读取文件权限
	 */
	private static String[] PERMISSIONS_PHONE = { "android.permission.READ_EXTERNAL_STORAGE" };

	public static boolean getFilePermissions(Activity activity) {
		if (Build.VERSION.SDK_INT < 23)
			return true;
		try {
			// 检测是否有权限
			int permission = ActivityCompat.checkSelfPermission(activity, "android.permission.READ_EXTERNAL_STORAGE");
			if (permission != PackageManager.PERMISSION_GRANTED) {
				// 用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
//				if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
//						"android.permission.READ_EXTERNAL_STORAGE")) {
//					ToastUtils.showToast(activity, "请开通相关权限，否则无法正常使用本功能！", Gravity.CENTER, 0, 0);
//				}
				// 没有权限，去申请权限，会弹出对话框
				ActivityCompat.requestPermissions(activity, PERMISSIONS_PHONE, 1);
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/*
	 * 允许拍照权限
	 */
	private static String[] PERMISSIONS_CAMERA = { "android.permission.CAMERA" };

	public static boolean getCameraPermissions(Activity activity) {
		try {
			// 检测是否有权限
			int permission = ActivityCompat.checkSelfPermission(activity, "android.permission.CAMERA");
			if (permission != PackageManager.PERMISSION_GRANTED) {
				// 没有权限，去申请权限，会弹出对话框
				ActivityCompat.requestPermissions(activity, PERMISSIONS_CAMERA, 1);
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * 千分位转换
	 */
	public static String thousandthNum(String num) {
		if (num == null || num.equals("")) {
			return "0.00";
		}
		// DecimalFormat df2 = new DecimalFormat("#,###.00");
		DecimalFormat df2 = new DecimalFormat(",##0.00");
		return df2.format(Double.valueOf(num));
	}

	/*
	 * 返回当前程序版本名
	 */
	public static String getAppVersionName(Context context) {
		String versionName = "";
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if (versionName == null || versionName.length() <= 0) {
				return "";
			}
		} catch (Exception e) {

		}
		return versionName;
	}
	/*
	 * 返回当前程序版本号
	 */
	public static int getAppVersionCode(Context context) {
		int versionCode = 0;
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionCode = pi.versionCode;
		} catch (Exception e) {

		}
		return versionCode;
	}


	/*
	 * 两个时间比较
	 */
	public static boolean getTwoDateResult(String time1, String time2) {// 如果第一个时间比第二个时间小，返回true
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d2 = df.parse((time1 + " 23:59:59"));
			Date d3 = df.parse((time2 + " 00:00:00"));
			if (d2.getTime() < d3.getTime()) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * 判断是否处于wife状态
	 */
	public static boolean isWifi(Context mContext) {
		ConnectivityManager connectivityManager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否为今天(效率比较高)
	 *
	 * @param day
	 *            传入的 时间 "2016-06-28 10:10:30" "2016-06-28" 都可以
	 * @return true今天 false不是
	 * @throws ParseException
	 */
	public static boolean IsToday(String day) throws ParseException {

		Calendar pre = Calendar.getInstance();
		Date predate = new Date(System.currentTimeMillis());
		pre.setTime(predate);
		Calendar cal = Calendar.getInstance();
		Date date = getDateFormat().parse(day);
		cal.setTime(date);
		if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
			int diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR);

			if (diffDay == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否为昨天(效率比较高)
	 *
	 * @param day
	 *            传入的 时间 "2016-06-28 10:10:30" "2016-06-28" 都可以
	 * @return true昨天 false不是
	 * @throws ParseException
	 */
	public static boolean IsYesterday(String day) throws ParseException {

		Calendar pre = Calendar.getInstance();
		Date predate = new Date(System.currentTimeMillis());
		pre.setTime(predate);

		Calendar cal = Calendar.getInstance();
		Date date = getDateFormat().parse(day);
		cal.setTime(date);

		if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
			int diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR);

			if (diffDay == -1) {
				return true;
			}
		}
		return false;
	}

	public static SimpleDateFormat getDateFormat() {
		if (null == DateLocal.get()) {
			DateLocal.set(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA));
		}
		return DateLocal.get();
	}

	private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();

	/**
	 * apk进行安装，兼容7.0以上手机
	 *
	 * apkPath:apk本地文件绝对路径
	 */
	public static void installNormal(Context context, String apkPath) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		// 版本在7.0以上是不能直接通过uri访问的
		if (Build.VERSION.SDK_INT < 24) {
			// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setDataAndType(Uri.fromFile(new File(apkPath)), "application/vnd.android.package-archive");
		} else {
			File file = (new File(apkPath));
			// 由于没有在Activity环境下启动Activity,设置下面的标签
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// 参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致 参数3 共享的文件
			Uri apkUri = FileProvider.getUriForFile(context, "cn.wsnex.unifiedapp.installapkdemo", file);
			// 添加这一句表示对目标应用临时授权该Uri所代表的文件
			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
		}
		context.startActivity(intent);
	}

	/**
	 * android判断一个Service是否存在
	 *
	 * className:service name
	 */
	public static boolean isServiceExisted(Context context, String className) {
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
		if (!(serviceList.size() > 0)) {
			return false;
		}
		for (int i = 0; i < serviceList.size(); i++) {
			RunningServiceInfo serviceInfo = serviceList.get(i);
			ComponentName serviceName = serviceInfo.service;
			if (serviceName.getClassName().equals(className)) {
				return true;
			}
		}
		return false;
	}
}