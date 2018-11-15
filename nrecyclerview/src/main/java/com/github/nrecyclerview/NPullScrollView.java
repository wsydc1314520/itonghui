package com.github.nrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.github.nrecyclerview.dirview.AppBarStateChangeListener;
import com.github.nrecyclerview.dirview.ArrowRefreshHeader;
import com.github.nrecyclerview.dirview.LoadingMoreFooter;
import com.github.nrecyclerview.dirview.ProgressStyle;
import com.github.nrecyclerview.interfaces.CustomFooterViewCallBack;
import com.github.nrecyclerview.interfaces.LoadingListener;

/**
 * 可刷新scrollview
 * Created by niuxinyu on 2018/4/18 0018.
 */

public class NPullScrollView extends ScrollView {
    private int mRefreshProgressStyle = ProgressStyle.BallSpinFadeLoader;
    private int mLoadingMoreProgressStyle = ProgressStyle.BallRotate;
    private LoadingListener mLoadingListener;
    private OnScrollChangeListener scrollViewListener = null;
    private ArrowRefreshHeader mRefreshHeader;
    private View mFootView;//上拉加载中布局

    private CustomFooterViewCallBack footerViewCallBack;
    private boolean pullRefreshEnabled = true;   //设置下拉刷新是否可用
    private boolean loadingMoreEnabled = false;  //设置上拉加载是否可用

    private static final float DRAG_RATE = 3;    //下拉刷新滑动阻力系数，越大需要手指下拉的距离越大才能刷新


    private boolean isLoadingData;      //是否加载数据
    private boolean isNoMore = false;   //是否还有数据

    private float mLastY = -1;          //上次触摸的的Y值
    private int topY;
    private boolean isAdded;

    private AppBarStateChangeListener.State appbarState = AppBarStateChangeListener.State.EXPANDED;


    public NPullScrollView(Context context) {
        this(context, null);
    }

    public NPullScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NPullScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        if (pullRefreshEnabled) {
            mRefreshHeader = new ArrowRefreshHeader(getContext());
            mRefreshHeader.setProgressStyle(mRefreshProgressStyle);
        }
        LoadingMoreFooter footView = new LoadingMoreFooter(getContext());
        footView.setProgressStyle(mLoadingMoreProgressStyle);
        mFootView = footView;
        mFootView.setVisibility(GONE);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!isAdded) {
                    isAdded = true;

                    //解决和AppBarLayout冲突的问题
                    ViewParent p = getParent();
                    while (p != null) {
                        if (p instanceof CoordinatorLayout) {
                            break;
                        }
                        p = p.getParent();
                    }

                    if (p != null) {
                        AppBarLayout appBarLayout = null;
                        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) p;
                        final int childCount = coordinatorLayout.getChildCount();
                        for (int i = childCount - 1; i >= 0; i--) {
                            final View child = coordinatorLayout.getChildAt(i);
                            if (child instanceof AppBarLayout) {
                                appBarLayout = (AppBarLayout) child;
                                break;
                            }
                        }

                        if (appBarLayout != null) {
                            appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
                                @Override
                                public void onStateChanged(AppBarLayout appBarLayout, State state) {
                                    appbarState = state;
                                }
                            });
                        }
                    }
                    setLayout();
                }
            }
        });
    }

    private void setLayout() {
        ViewGroup group = (ViewGroup) getParent();
        LinearLayout container = new LinearLayout(getContext());
        container.setOrientation(LinearLayout.VERTICAL);
        int index = group.indexOfChild(this);
        group.removeView(this);
        group.addView(container, index, getLayoutParams());

        container.addView(mRefreshHeader, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        container.addView(this, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((ViewGroup)this.getChildAt(0)).addView(mFootView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    /**
     * 设置下拉刷新上拉加载回调
     */
    public void setLoadingListener(LoadingListener listener) {
        mLoadingListener = listener;
    }

    /**
     * 设置自定义的header
     */
    public void setRefreshHeader(ArrowRefreshHeader refreshHeader) {
        mRefreshHeader = refreshHeader;
    }

    /**
     * 获取默认header
     */
    public ArrowRefreshHeader getDefaultRefreshHeaderView() {
        if (mRefreshHeader == null) {
            return null;
        }
        return mRefreshHeader;
    }

    /**
     * 获取默认footer
     */
    public LoadingMoreFooter getDefaultFootView() {
        if (mFootView == null) {
            return null;
        }
        if (mFootView instanceof LoadingMoreFooter) {
            return ((LoadingMoreFooter) mFootView);
        }
        return null;
    }

    /**
     * 获取footer
     */
    public View getFootView() {
        return mFootView;
    }

    /**
     * 设置footer
     */
    @SuppressWarnings("all")
    public void setFootView(@NonNull final View view, @NonNull CustomFooterViewCallBack footerViewCallBack) {
        if (view == null || footerViewCallBack == null) {
            return;
        }
        mFootView = view;
        this.footerViewCallBack = footerViewCallBack;
    }


    /**
     * 下拉刷新是否可用
     */
    public void setPullRefreshEnabled(boolean enabled) {
        pullRefreshEnabled = enabled;
    }

    /**
     * 设置上拉加载是否可用，默认不可用
     */
    public void setLoadingMoreEnabled(boolean loadingMoreEnabled) {
        this.loadingMoreEnabled = loadingMoreEnabled;
    }

    /**
     * 设置下拉刷新的进度条风格
     */
    public void setRefreshProgressStyle(int style) {
        mRefreshProgressStyle = style;
        if (mRefreshHeader != null) {
            mRefreshHeader.setProgressStyle(style);
        }
    }

    /**
     * 设置上拉加载的进度条风格
     */
    public void setLoadingMoreProgressStyle(int style) {
        mLoadingMoreProgressStyle = style;
        if (mFootView instanceof LoadingMoreFooter) {
            ((LoadingMoreFooter) mFootView).setProgressStyle(style);
        }
    }

    /**
     * 设置下拉刷新的箭头图标
     */
    public void setArrowImageView(int resId) {
        if (mRefreshHeader != null && mRefreshHeader instanceof ArrowRefreshHeader) {
            mRefreshHeader.setArrowImageView(resId);
        }
    }


    /**
     * 手动调用直接刷新，无下拉效果
     */
    public void refresh() {
        if (pullRefreshEnabled && mLoadingListener != null) {
            isLoadingData = true;
            mLoadingListener.onRefresh();
        }
    }

    /**
     * 手动调用下拉刷新，有下拉效果
     */
    public void refreshWithPull() {
        if (pullRefreshEnabled && mLoadingListener != null) {
            isLoadingData = true;
            mRefreshHeader.setState(ArrowRefreshHeader.STATE_REFRESHING);
            mLoadingListener.onRefresh();
        }
    }

    /**
     * 刷新完成
     */
    public void refreshComplete() {
        isLoadingData = false;
        if (mRefreshHeader != null){
            mRefreshHeader.refreshComplete();
        }
        setNoMore(false);
    }

    /**
     * 加载完成
     */
    public void loadMoreComplete() {
        isLoadingData = false;
        if (mFootView instanceof LoadingMoreFooter) {
            ((LoadingMoreFooter) mFootView).setState(LoadingMoreFooter.STATE_COMPLETE);
        } else {
            if (footerViewCallBack != null) {
                footerViewCallBack.onLoadMoreComplete(mFootView);
            }
        }
    }

    /**
     * 没有更多设置
     */
    public void setNoMore(boolean noMore) {
        isLoadingData = false;
        isNoMore = noMore;
        if (mFootView instanceof LoadingMoreFooter) {
            ((LoadingMoreFooter) mFootView).setState(isNoMore ? LoadingMoreFooter.STATE_NOMORE : LoadingMoreFooter.STATE_COMPLETE);
        } else {
            if (footerViewCallBack != null) {
                footerViewCallBack.onSetNoMore(mFootView, noMore);
            }
        }
    }

    /**
     * 重置
     */
    public void reset() {
        setNoMore(false);
        loadMoreComplete();
        refreshComplete();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mLastY == -1) {
            mLastY = ev.getRawY();
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY;
                mLastY = ev.getRawY();
                //滚动到最底布
                Log.e("onLoadMore:","isBottom:"+isBottom()+" "+(mRefreshHeader.getState() < ArrowRefreshHeader.STATE_REFRESHING)+" isLoadingData:"+isLoadingData);
                if (isOnTop() && pullRefreshEnabled && appbarState == AppBarStateChangeListener.State.EXPANDED) {
                    if (mRefreshHeader == null)
                        break;
                    mRefreshHeader.onMove(deltaY / DRAG_RATE);
                    if (mRefreshHeader.getVisibleHeight() > 0 && mRefreshHeader.getState() < ArrowRefreshHeader.STATE_REFRESHING) {
                        return false;
                    }
                }else if (isBottom() && loadingMoreEnabled && !isNoMore && mRefreshHeader.getState() < ArrowRefreshHeader.STATE_REFRESHING&&!isLoadingData) {
                    isLoadingData = true;
                    if (mFootView instanceof LoadingMoreFooter) {

                        ((LoadingMoreFooter) mFootView).setState(LoadingMoreFooter.STATE_LOADING);
                    } else {
                        if (footerViewCallBack != null) {
                            footerViewCallBack.onLoadingMore(mFootView);
                        }
                    }
                    mLoadingListener.onLoadMore();
                }
                break;
            default:
                mLastY = -1; // reset
                if (isOnTop() && pullRefreshEnabled && appbarState == AppBarStateChangeListener.State.EXPANDED) {
                    if (mRefreshHeader != null && mRefreshHeader.releaseAction()) {
                        if (mLoadingListener != null) {
                            mLoadingListener.onRefresh();
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        topY = t;
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChange(this, l, t, oldl, oldt);
        }
    }

    /**
     * 是否滚动到顶部
     */
    private boolean isOnTop() {
        return topY == 0;
    }

    /**
     * 滚动到底部
     */
    private boolean isBottom() {
//        View childView = getChildAt(0);
//        if (mLastY == (childView.getHeight() - getHeight())) {
//            return true;
//        }
        View childAt = getChildAt(0);
        if (childAt.getMeasuredHeight() <= getHeight() + getScrollY()) {
            return true;
        }
        return false;
    }

    public void setScrollViewListener(OnScrollChangeListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }


    public interface OnScrollChangeListener {
        void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }
}