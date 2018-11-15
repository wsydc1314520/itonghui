package com.itonghui.tfdz.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.itonghui.tfdz.MainActivity;
import com.itonghui.tfdz.adapter.GuideViewPagerAdapter;
import com.itonghui.tfdz.R;
import com.itonghui.tfdz.app.ActivitySupport;
import com.itonghui.tfdz.config.Constant;
import com.itonghui.tfdz.util.PreferUtil;

import java.util.ArrayList;
/**
 *  WelcomeActivity
 *  @author yandaocheng <br/>
 *	引导页
 *	2018-04-23
 *	修改者，修改日期，修改内容
 */
public class GuideActivity extends ActivitySupport {

    private ViewPager mViewPager;
    private LinearLayout mLayout_point;
    private ImageView mBtnStar;

    // 适配器
    private GuideViewPagerAdapter adapter;
    // 数据源
    private ArrayList<Integer> imagelist = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initData();
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mLayout_point = (LinearLayout) findViewById(R.id.guide_layout);
        mViewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        mViewPager.addOnPageChangeListener(new GuidePageListener());
        adapter = new GuideViewPagerAdapter(getSupportFragmentManager(),imagelist);
        adapter.showPoint(this, mLayout_point);
        mViewPager.setAdapter(adapter);

        mBtnStar = (ImageView) findViewById(R.id.guide_star);
        mBtnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                PreferUtil.setPrefBoolean(GuideActivity.this, Constant.IS_FIRSTSTART, false);
                finish();
            }
        });
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        imagelist.add(R.mipmap.welcome_page_1);
        imagelist.add(R.mipmap.welcome_page_2);
        imagelist.add(R.mipmap.welcome_page_3);
    }

    /**
     * viewpager的滑动监听
     */
    class GuidePageListener implements ViewPager.OnPageChangeListener {

        // 某个页面被选中
        @Override
        public void onPageSelected(int position) {
            for (int i = 0; i < imagelist.size(); i++) {
                mLayout_point.getChildAt(i).setBackgroundResource(
                        R.drawable.xml_shape_point_gray);
                if (i == position) {
                    mLayout_point.getChildAt(i).setBackgroundResource(
                            R.drawable.xml_shape_point_red);
                }
            }
            //是否是最后一个页面
            if (position == imagelist.size() - 1) {
                mBtnStar.setVisibility(View.VISIBLE);
            } else {
                mBtnStar.setVisibility(View.GONE);
            }
        }

        // 滑动状态发生变化
        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

    }
}
