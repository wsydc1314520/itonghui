package com.itonghui.tfdz.adapter.recycleviewAdapter;


/**
 * 示列代码
 */
public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}
