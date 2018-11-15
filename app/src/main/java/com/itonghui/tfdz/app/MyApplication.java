package com.itonghui.tfdz.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.itonghui.tfdz.okhttp.Https;
import com.itonghui.tfdz.okhttp.OkHttpUtils;
import java.io.File;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import okhttp3.Cache;
import okhttp3.OkHttpClient;


public class MyApplication extends Application {
    public static Context mContext;
    private static List<Activity> activityList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initOkHttp();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {
                    DHActivityManager.getManager().setCurrentActivity(activity);
                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {

                }
            });
        }
    }

    public static class DHActivityManager{
        private static DHActivityManager manager = new DHActivityManager();
        private WeakReference<Activity> current;
        private DHActivityManager(){

        }
        public static DHActivityManager getManager(){
            return manager;
        }

        public Activity getTopActivity(){
            if (current!=null)
                return current.get();
            return null;
        }
        public void setCurrentActivity(Activity obj){
            current = new WeakReference<Activity>(obj);
        }
    }

    public static Context getContext() {
        return mContext;
    }
    /**
     *初始化OkHttp
     */
    private void initOkHttp() {
        File cache = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(mContext));
        Https.SSLParams sslParams = Https.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)//连接超时(单位:秒)
                .writeTimeout(20, TimeUnit.SECONDS)//写入超时(单位:秒)
                .readTimeout(20, TimeUnit.SECONDS)//读取超时(单位:秒)
                .pingInterval(20, TimeUnit.SECONDS) //websocket轮训间隔(单位:秒)
                .cache(new Cache(cache.getAbsoluteFile(), cacheSize))//设置缓存
                .cookieJar(cookieJar)//Cookies持久化
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)//https配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
        Glide.get(mContext).getRegistry().replace(GlideUrl.class,InputStream.class,new OkHttpUrlLoader.Factory(okHttpClient));
    }

    /**
     * 添加Activity到activityList，在onCreate()中调用
     */
    public static void addActivity(Activity activity) {
        if (activityList != null && activityList.size() > 0) {
            if (!activityList.contains(activity)) {
                activityList.add(activity);
            }
        } else {
            activityList.add(activity);
        }
    }

    /**
     *结束Activity到activityList，在onDestroy()中调用
     */
    public static void finishActivity(Activity activity) {
        if (activity != null && activityList != null && activityList.size() > 0) {
            activityList.remove(activity);
        }
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        if (activityList != null && activityList.size() > 0) {
            for (Activity activity : activityList) {
                if (null != activity) {
                    activity.finish();
                }
            }
        }
        activityList.clear();
    }
}
