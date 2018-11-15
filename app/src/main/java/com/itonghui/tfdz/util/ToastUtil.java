package com.itonghui.tfdz.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * toast封装
 */
public class ToastUtil{

	private static Toast mToast;
	
	/**
	 * 普通toast
	 * @param context
	 * @param text
	 */
	public static void showToast(Context context, String text){
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		mToast.show();
	}
	
	/**
	 * 设置显示时间toast
	 * @param context
	 * @param text
	 */
	public static void showToast(Context context, String text, int duration){
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(context, text, duration);
		mToast.show();
	}
	
	/**
	 * 设置显示位置toast
	 */
	public static void showToast(Context context, String text, int gravity, int xOffset, int yOffset){
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		mToast.setGravity(gravity, xOffset, yOffset);
		mToast.show();
	}
	
	/**
	 * ָ设置显示时间及显示位置
	 */
	public static void showToast(Context context, String text, int duration, int gravity, int xOffset, int yOffset){
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(context, text,duration);
		mToast.setGravity(gravity, xOffset, yOffset);
		mToast.show();
	}
	
	
	/**
	 * 设置带图片toast
	 * @param context
	 * @param text
	 * @param imgId
	 */
	public static void showToastWithImg(Context context, String text, int imgId){
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(context,text, Toast.LENGTH_SHORT);
		mToast.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout toastView = (LinearLayout) mToast.getView();
		ImageView imageview = new ImageView(context);
		imageview.setImageResource(imgId);
		imageview.setPadding(0, 24, 0, 0);
		toastView.addView(imageview, 0);
		mToast.show();
	}
	
	/**
	 * 设置带图片且显示位置
	 * @param context
	 * @param text
	 * @param imgId
	 */
	public static void showToastWithImg(Context context, String text, int imgId, int gravity, int xOffset, int yOffset){
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(context,text, Toast.LENGTH_SHORT);
		mToast.setGravity(gravity, xOffset, yOffset);
		LinearLayout toastView = (LinearLayout) mToast.getView();
		ImageView imageview = new ImageView(context);
		imageview.setImageResource(imgId);
		imageview.setPadding(0, 24, 0, 0);
		toastView.addView(imageview, 0);
		mToast.show();
	}

	/**
	 * 设置带图片，显示时间 显示位置
	 * @param context
	 * @param text
	 * @param imgId
	 */
	public static void showToastWithImg(Context context, String text, int imgId, int duration, int gravity, int xOffset, int yOffset){
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(context,text, duration);
		mToast.setGravity(gravity, xOffset, yOffset);
		LinearLayout toastView = (LinearLayout) mToast.getView();
		ImageView imageview = new ImageView(context);
		imageview.setImageResource(imgId);
		imageview.setPadding(0, 24, 0, 0);
		toastView.addView(imageview, 0);
		mToast.show();
	}
}
