package com.itonghui.tfdz.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.itonghui.tfdz.dialog.LoadingDialog;

/**
 *  FragmentSupport
 *  @author yandaocheng <br/>
 *	帮助支持类
 *	2017-12-18
 *	修改者，修改日期，修改内容
 */
public class FragmentSupport extends Fragment{
	protected Activity mActivity = null;
	private Dialog dialog;

	@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}


	public void showProgressDialog(){
		if (dialog == null) {
			dialog = LoadingDialog.createLoadingDialog(mActivity, "载入中...");
			dialog.show();
		}
	}
	
	public void showProgressDialog(String msg){
		if (dialog == null) {
			dialog = LoadingDialog.createLoadingDialog(mActivity, msg);
			dialog.show();
		}
	}
	
	public void closeProgressDialog(){
		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}
	}
}
