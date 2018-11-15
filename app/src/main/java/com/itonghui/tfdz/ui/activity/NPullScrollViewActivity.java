package com.itonghui.tfdz.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import com.github.nrecyclerview.NPullScrollView;
import com.github.nrecyclerview.interfaces.LoadingListener;
import com.itonghui.tfdz.R;
import com.itonghui.tfdz.app.ActivitySupport;
/**
 * 示列代码
 */
public class NPullScrollViewActivity extends ActivitySupport {

    private NPullScrollView mScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npull_scroll_view);
        initView();
    }

    private void initView() {
        mScrollView = findViewById(R.id.n_scroll_view);
//        mScrollView.setLoadingMoreEnabled(true);//启用上拉加载
//        mScrollView.setPullRefreshEnabled(false);//禁用下拉刷新
//        mScrollView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);////显示刷新时间
//        mScrollView.setRefreshProgressStyle(ProgressStyle.SysProgress);//设置刷新loading样式
//        mScrollView.setLoadingMoreProgressStyle(ProgressStyle.SysProgress);//设置加载loading样式
//        mScrollView.getDefaultFootView().setLoadingHint("自定义加载中提示");
//        mScrollView.getDefaultFootView().setNoMoreHint("自定义加载完毕提示");
        mScrollView.setLoadingListener(new LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mScrollView.refreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mScrollView.loadMoreComplete();
                    }
                }, 1000);
            }
        });
    }
}
