package com.itonghui.tfdz.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itonghui.tfdz.R;


/**
 *  MainHomeFragment
 *  @author yandaocheng <br/>
 *	首页
 *	2018-04-23
 *	修改者，修改日期，修改内容
 */

public class MainHomeFragment extends Fragment {

    public static Fragment getInstance() {
        return new MainHomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    // 填充数据
    private void initData() {
    }

}
