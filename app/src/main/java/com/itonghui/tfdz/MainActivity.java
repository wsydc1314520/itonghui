package com.itonghui.tfdz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.itonghui.tfdz.adapter.VPMainAdapter;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.itonghui.tfdz.ui.fragment.MainCartFragment;
import com.itonghui.tfdz.ui.fragment.MainCenterFragment;
import com.itonghui.tfdz.ui.fragment.MainClassesFragment;
import com.itonghui.tfdz.ui.fragment.MainHomeFragment;
import com.itonghui.tfdz.view.NoSlideViewPage;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.List;
/**
 *  MainActivity
 *  @author yandaocheng <br/>
 *	底部基本框架
 *	2018-04-23
 *	修改者，修改日期，修改内容
 */
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    @ViewInject(com.itonghui.tfdz.R.id.main_viewpager)
    private NoSlideViewPage mViewPager;
    @ViewInject(com.itonghui.tfdz.R.id.main_tab_rg)
    private RadioGroup mRGTab;

    private List<Fragment> fragments = new ArrayList<>();
    private VPMainAdapter viewPageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.itonghui.tfdz.R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ViewUtils.inject(this);
        initView();
    }

    /**
     * 初始化页面控件
     */
    private void initView() {
        mRGTab.setOnCheckedChangeListener(this);
        fragments.add(MainHomeFragment.getInstance());
        fragments.add(MainClassesFragment.getInstance());
        fragments.add(MainCartFragment.getInstance());
        fragments.add(MainCenterFragment.getInstance());
        viewPageAdapter = new VPMainAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setOffscreenPageLimit(4);//缓存4个页面
        mViewPager.setAdapter(viewPageAdapter);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case com.itonghui.tfdz.R.id.main_tab_one:
                mViewPager.setCurrentItem(0);
                break;
            case com.itonghui.tfdz.R.id.main_tab_two:
                mViewPager.setCurrentItem(1);
                break;
            case com.itonghui.tfdz.R.id.main_tab_three:
                mViewPager.setCurrentItem(2);
                break;
            case com.itonghui.tfdz.R.id.main_tab_four:
                mViewPager.setCurrentItem(3);
                break;
            default:
                mViewPager.setCurrentItem(0);
                break;
        }
    }
}
