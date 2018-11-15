package com.itonghui.tfdz.adapter;

import java.util.ArrayList;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.itonghui.tfdz.R;
import com.itonghui.tfdz.ui.fragment.ImageFragment;

public class GuideViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Integer> imagelist;

    public GuideViewPagerAdapter(FragmentManager fm,ArrayList<Integer> imagelist) {
        super(fm);
        this.imagelist=imagelist;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.getInstance(imagelist.get(position));
    }

    @Override
    public int getCount() {
        return imagelist.size();
    }

    /**
     * 自定义方法，向传入布局中添加小圆点，减少主页面Activity的代码
     * @param context
     * @param parent
     */
    public void showPoint(Context context, ViewGroup parent) {
        // 初始化引导页的
        for (int i = 0; i < imagelist.size(); i++) {
            View point = new View(context);
            if (i == 0) {
                point.setBackgroundResource(R.drawable.xml_shape_point_red);// 设置引导页默认圆点
            } else {
                point.setBackgroundResource(R.drawable.xml_shape_point_gray);// 设置引导页默认圆点
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    20, 20);
            if (i > 0) {
                params.leftMargin = 20;// 设置圆点间隔
            }
            point.setLayoutParams(params);// 设置圆点的大小
            parent.addView(point);// 将圆点添加给线性布局

        }

    }

}
