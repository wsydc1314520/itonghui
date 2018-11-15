package com.itonghui.tfdz.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.itonghui.tfdz.MainActivity;
import com.itonghui.tfdz.R;
import com.itonghui.tfdz.app.ActivitySupport;
import com.itonghui.tfdz.config.Constant;
import com.itonghui.tfdz.util.PreferUtil;

/**
 *  WelcomeActivity
 *  @author yandaocheng <br/>
 *	欢迎页
 *	2018-04-23
 *	修改者，修改日期，修改内容
 */
public class WelcomeActivity extends ActivitySupport {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		WindowManager wm = (WindowManager) getContext().getSystemService(context.WINDOW_SERVICE);
		Constant.displayWidth = wm.getDefaultDisplay().getWidth();
		Constant.displayHeight = wm.getDefaultDisplay().getHeight();
		initData();
	}

	private void initData(){
		boolean isfirst = PreferUtil.getPrefBoolean(this, Constant.IS_FIRSTSTART, true);
		if (isfirst) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					/**
					 * 程序第一次使用进入Guide页面
					 */
					startActivity(new Intent(WelcomeActivity.this,GuideActivity.class));
					overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
					finish();
				}
			}, Constant.TIME_DELAY_WELCOME);
		} else {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					/**
					 * 程序不是第一次使用进入程序
					 */
					startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
					overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
					finish();
				}
			}, Constant.TIME_DELAY_WELCOME);
		}
	}
}
