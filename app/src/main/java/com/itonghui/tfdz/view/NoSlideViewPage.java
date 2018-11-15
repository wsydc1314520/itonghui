package com.itonghui.tfdz.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoSlideViewPage extends ViewPager {
	private boolean isPagingEnabled = false;//不可左右滑动

	public NoSlideViewPage(Context context) {
		super(context);
	}

	public NoSlideViewPage(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return this.isPagingEnabled && super.onTouchEvent(event);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		return this.isPagingEnabled && super.onInterceptTouchEvent(event);
	}

	public void setPagingEnabled(boolean b) {
		this.isPagingEnabled = b;
	}
	
	@Override
    public void setCurrentItem(int item) {
        //false 去除滚动效果
        super.setCurrentItem(item,false);
    }
}