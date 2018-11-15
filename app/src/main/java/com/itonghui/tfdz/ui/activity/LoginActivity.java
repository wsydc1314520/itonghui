package com.itonghui.tfdz.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.itonghui.tfdz.util.GlideUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.itonghui.tfdz.R;
import com.itonghui.tfdz.bean.ResultDesc;
import com.itonghui.tfdz.config.Constant;
import com.itonghui.tfdz.okhttp.HttpCallback;
import com.itonghui.tfdz.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 登录
 * Created by yandaocheng on 2017/9/12 0012.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.lg_login_btn)
    private Button BtnLogin; // 登录
    @ViewInject(R.id.lg_login_accounts)
    private EditText EtAccounts; // 账户
    @ViewInject(R.id.lg_password)
    private EditText EtPassword; // 密码
    @ViewInject(R.id.lg_verify_info)
    private EditText EtVerify; // 验证码
    @ViewInject(R.id.lg_click_btn)
    private ImageView IvGetVerify; // 获取验证码


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewUtils.inject(this);
        initToolbar();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDate();
    }
    /**
     * 初始化标题栏
     */
    private void initToolbar() {
        IvGetVerify.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        Button BtnRegist = (Button) findViewById(R.id.lg_register);
        BtnRegist.setOnClickListener(this);
        TextView TvForgetPass = (TextView) findViewById(R.id.lg_forget_password);
        TvForgetPass.setOnClickListener(this);
        IvGetVerify.setOnClickListener(this);
        BtnLogin.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initDate() {
        getVerity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lg_click_btn:
                getVerity();
                break;
            case R.id.lg_login_btn:
                submitView();
                break;
            default:
                break;
        }
    }

    private void submitView(){

        String verify = EtVerify.getText().toString().trim(); // 图片验证码
        Map<String, String> mMap = new HashMap<>();
        mMap.put("userName", "13965094557"); // 手机号码
        OkHttpUtils.postAsyn(Constant.AppCheckAccount, mMap, new HttpCallback<ResultDesc>() {
            @Override
            public void onSuccess(ResultDesc response) {
                super.onSuccess(response);
                Toast.makeText(getApplicationContext(),response.getStatusCode()+"", Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * 获取图片验证码
     */
    private void getVerity() {
        int randomData = new Random().nextInt(50000);
        String imageUrl =Constant.AppImgVerity + "?data=" + randomData;
        GlideUtil.load(this, imageUrl, IvGetVerify, GlideUtil.getOption());
    }
}
