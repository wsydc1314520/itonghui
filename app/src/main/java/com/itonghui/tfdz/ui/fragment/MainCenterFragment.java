package com.itonghui.tfdz.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.itonghui.tfdz.ui.activity.NPullScrollViewActivity;
import com.itonghui.tfdz.ui.activity.NRecyclerViewActivityTwo;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.itonghui.tfdz.R;
import com.itonghui.tfdz.ui.activity.LoginActivity;
import com.itonghui.tfdz.ui.activity.NRecyclerViewActivity;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;
import com.yzq.zxinglibrary.encode.CodeCreator;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 *  MainHomeFragment
 *  @author yandaocheng <br/>
 *	个人中心
 *	2018-04-23
 *	修改者，修改日期，修改内容
 */


public class MainCenterFragment extends Fragment {
    @ViewInject(R.id.go_login)
    private TextView mGoLogin;
    @ViewInject(R.id.go_recycle)
    private TextView mGoRecycle;
    @ViewInject(R.id.go_scroll)
    private TextView mGoScroll;
    @ViewInject(R.id.go_recycle_two)
    private TextView mGoRecycleTwo;
    @ViewInject(R.id.qr_code)
    private TextView mQRCode;           //二维码扫描
    @ViewInject(R.id.encodeBtnWithLogo)
    private Button mEncodeWithLog;
    @ViewInject(R.id.contentIvWithLogo)
    private ImageView mContentWithLog;

    public static Fragment getInstance() {
        return new MainCenterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_center, container, false);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    // 填充数据
    private void initView() {
        mGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));

            }
        });
        mGoRecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NRecyclerViewActivity.class));
            }
        });
        mGoScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NPullScrollViewActivity.class));
            }
        });
        mGoRecycleTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NRecyclerViewActivityTwo.class));
            }
        });
        mQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndPermission.with(getActivity())
                        .permission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE)
                        .onGranted(new Action() {
                            @Override
                            public void onAction(List<String> permissions) {
                                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                                /*ZxingConfig是配置类
                                 *可以设置是否显示底部布局，闪光灯，相册，
                                 * 是否播放提示音  震动
                                 * 设置扫描框颜色等
                                 * 也可以不传这个参数
                                 * */
                                ZxingConfig config = new ZxingConfig();
//                                config.setPlayBeep(true);//是否播放扫描声音 默认为true
//                                config.setShake(true);//是否震动  默认为true
//                                config.setDecodeBarCode(true);//是否扫描条形码 默认为true
                                config.setReactColor(R.color.im_color);//设置扫描框四个角的颜色 默认为白色
                                config.setFrameLineColor(R.color.im_color);//设置扫描框边框颜色 默认无色
                                config.setScanLineColor(R.color.im_color);//设置扫描线的颜色 默认白色
                                config.setFullScreenScan(false);//是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
                                intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
                                startActivityForResult(intent, 111);
                            }
                        })
                        .onDenied(new Action() {
                            @Override
                            public void onAction(List<String> permissions) {
                                Uri packageURI = Uri.parse("package:" + getActivity().getPackageName());
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                Toast.makeText(getActivity(), "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
                            }
                        }).start();
            }
        });
        mEncodeWithLog.setOnClickListener(new View.OnClickListener() {//生成图片二维码，隐藏的为不带logo二维码
            @Override
            public void onClick(View view) {
                Bitmap bitmap = null;
                try {
                    Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                    bitmap = CodeCreator.createQRCode("默认字符串", 400, 400, logo);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
                if (bitmap != null) {
                    mContentWithLog.setImageBitmap(bitmap);
                }

//                Bitmap bitmap = null;
//                try {
//                    bitmap = CodeCreator.createQRCode("默认字符串", 400, 400, null);
//
//                } catch (WriterException e) {
//                    e.printStackTrace();
//                }
//                if (bitmap != null) {
//                    mContentWithLog.setImageBitmap(bitmap);
//                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == 111 && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(Constant.CODED_CONTENT);
                Toast.makeText(getActivity(),"扫描结果为：" + content,Toast.LENGTH_LONG).show();
            }
        }
    }
}
