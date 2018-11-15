package com.itonghui.tfdz.util;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.itonghui.tfdz.R;

import java.io.File;

/**
 * GlideUtil
 *
 * @author yandaocheng <br/>
 * glide加载图片
 * 2018-08-17
 * 修改者，修改日期，修改内容
 */
public class GlideUtil {
    public static void load(Context context,
                            String url,
                            ImageView imageView,
                            RequestOptions options) {
        Glide.with(context)
//                .asBitmap()//只会加载成静态图片
                .load(url)
                .apply(options)
                .into(imageView);
    }

    //默认缓存图片
    public static RequestOptions getOption() {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.default_image)
                .error(R.mipmap.default_image)
//                .centerCrop()
//                .circleCrop()//加载成圆形
//                .override(500, 500)//指定大小
//                .skipMemoryCache(true)//禁用内存缓存
                .diskCacheStrategy(DiskCacheStrategy.ALL);
//        DiskCacheStrategy.NONE： 表示不缓存任何内容。
//        DiskCacheStrategy.DATA： 表示只缓存原始图片。
//        DiskCacheStrategy.RESOURCE： 表示只缓存转换过后的图片。
//        DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
//        DiskCacheStrategy.AUTOMATIC： 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。

//        1.android:scaleType=“center”
//        保持原图的大小，显示在ImageView的中心。当原图的size大于ImageView的size时，多出来的部分被截掉。
//        2.android:scaleType=“center_inside”
//        以原图正常显示为目的，如果原图大小大于ImageView的size，就按照比例缩小原图的宽高，居中显示在ImageView中。如果原图size小于ImageView的size，则不做处理居中显示图片。
//        3.android:scaleType=“center_crop”
//        以原图填满ImageView为目的，如果原图size大于ImageView的size，则与center_inside一样，按比例缩小，居中显示在ImageView上。如果原图size小于ImageView的size，则按比例拉升原图的宽和高，填充ImageView居中显示。
//        4.android:scaleType=“matrix”
//        不改变原图的大小，从ImageView的左上角开始绘制，超出部分做剪切处理。
//        5.androd:scaleType=“fit_xy”
//        把图片按照指定的大小在ImageView中显示，拉伸显示图片，不保持原比例，填满ImageView.
//        6.android:scaleType=“fit_start”
//        把原图按照比例放大缩小到ImageView的高度，显示在ImageView的start（前部/上部）。
//        7.android:sacleType=“fit_center”
//        把原图按照比例放大缩小到ImageView的高度，显示在ImageView的center（中部/居中显示）。
//        8.android:scaleType=“fit_end”
//        把原图按照比例放大缩小到ImageView的高度，显示在ImageVIew的end（后部/尾部/底部）
        return options;
    }

    //图片下载
    public static void downloadImage(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = "http://www.guolin.tech/book.png";
                    FutureTarget<File> target = Glide.with(context)
                            .asFile()
                            .load(url)
                            .submit();
                    final File imageFile = target.get();
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, imageFile.getPath(), Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
