package com.itonghui.tfdz.ui.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/**
 * 轮播图图片
 * 
 * @author yandaocheng
 * 
 */
public class ImageFragment extends Fragment {

	public static Fragment getInstance(int imageid) {
		ImageFragment fragment = new ImageFragment();
		Bundle args = new Bundle();
		args.putInt("imageid", imageid);
		fragment.setArguments(args);
		return fragment;
	}

	private int imageId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		imageId = getArguments().getInt("imageid");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ImageView imageView = new ImageView(getActivity());
		imageView.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		imageView.setScaleType(ScaleType.FIT_XY);
		imageView.setImageResource(imageId);
		return imageView;
	}

}
