package com.itonghui.tfdz.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.itonghui.tfdz.dialog.LoadingDialog;

/**
 *  ActivitySupport
 *  @author yandaocheng <br/>
 *	帮助支持类
 *	2017-12-18
 *	修改者，修改日期，修改内容
 */
public class ActivitySupport extends AppCompatActivity implements ISupport {
	protected Context context = null;
	protected MyApplication myApplication;
	private Dialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  
		context = this;
		myApplication = (MyApplication) getApplication();
		myApplication.addActivity(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		MyApplication.finishActivity(this);
	}
	
	public void showProgressDialog(){
		if (dialog == null) {
			dialog = LoadingDialog.createLoadingDialog(this, "载入中...");
			dialog.show();
		}
	}
	
	public void showProgressDialog(String msg){
		if (dialog == null) {
			dialog = LoadingDialog.createLoadingDialog(this, msg);
			dialog.show();
		}
	}
	
	public void closeProgressDialog(){
		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}
	}
	/**
	 * 关闭键盘事件
	 */
	public void closeInput() {
		InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (inputMethodManager != null && this.getCurrentFocus() != null) {
			inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus()
					.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	@Override
	public MyApplication getMyApplication() {
		return myApplication;
	}

	@Override
	public Context getContext() {
		return context;
	}


}
