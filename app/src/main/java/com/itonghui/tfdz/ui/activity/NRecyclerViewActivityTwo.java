package com.itonghui.tfdz.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.github.nrecyclerview.NRecyclerView;
import com.github.nrecyclerview.interfaces.LoadingListener;
import com.itonghui.tfdz.R;
import com.itonghui.tfdz.adapter.BaseAdapter;
import com.itonghui.tfdz.adapter.recycleviewAdapter.CommonAdapter;
import com.itonghui.tfdz.adapter.recycleviewAdapter.ViewHolder;
import com.itonghui.tfdz.app.ActivitySupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 示列代码
 */

public class NRecyclerViewActivityTwo extends ActivitySupport {


    private NRecyclerView mRecyclerView;
    private CommonAdapter<String> mAdapter;
    private ArrayList<String> listData = new ArrayList<>();

    private int mTotalCount = 50;
    private int mPage = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nrecyclerview);
        initView();
        initData(0);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mRecyclerView = findViewById(R.id.n_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setPullRefreshEnabled(false);//是否禁用刷新
//        mRecyclerView.setLoadingMoreEnabled(flase);//是否禁用加载
//        mRecyclerView.setRefreshProgressStyle(ProgressStyle.SysProgress);//设置刷新loading样式
//        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SysProgress);//设置加载loading样式
//        mRecyclerView .getDefaultRefreshHeaderView().setRefreshTimeVisible(true);//显示刷新时间
//        mRecyclerView.setLimitNumberToCallLoadMore(2);//修改当item还有还剩余几个未加载的时候执行onLoadMore，默认为1
//        mRecyclerView.getDefaultFootView().setLoadingHint("自定义加载中提示");
//        mRecyclerView.getDefaultFootView().setLoadingDoneHint("自定义加载完成提示");
//        mRecyclerView.getDefaultFootView().setNoMoreHint("自定义已没有加载数据提示");
//        mAdapter = new BaseAdapter(this, listData);

        mAdapter = new CommonAdapter<String>(this, R.layout.item_two, listData){
            @Override
            protected void convert(ViewHolder holder, String s, int position)
            {
                holder.setText(R.id.text, s + " : " + holder.getAdapterPosition() + " , " + holder.getLayoutPosition());
                holder.setImageUrl(R.id.re_image,"https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/whfpf%3D180%2C140%2C50/sign=4bfff88add58ccbf1be9e67a7fe58d06/9213b07eca806538f4f6f52b9bdda144ad3482ee.jpg");
            }
        };

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLoadingListener(new LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mPage = 0;
                        listData.clear();
                        initData(mPage);
                    }

                }, 1000);
            }

            @Override
            public void onLoadMore() {
                ++mPage;
                initData(mPage);
            }
        });
    }

    private void initData(int mPage) {
        mRecyclerView.refreshComplete();//刷新成功
        for (int i = 0; i < 10; i++) {
            listData.add("item" + (10 * mPage + i));
        }
        mAdapter.notifyDataSetChanged();
        if (listData.size() == mTotalCount) {
            mRecyclerView.setNoMore(true);//没有更多了
        } else {
            mRecyclerView.loadMoreComplete();//加载成功
        }
    }

}
