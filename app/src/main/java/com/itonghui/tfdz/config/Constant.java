package com.itonghui.tfdz.config;

/**
 * 公共静态变量
 */

public class Constant {
    /**
     * 欢迎页停留时间
     */
    public final static int TIME_DELAY_WELCOME = 2000;
    /**
     * 域名
     */
	public final static String URL = "http://58.215.243.229:48086/";
    /**
     * 阎道成 --- 接口
     */
    public static final String AppImgVerity = URL+ "captcha"; //图片验证码
    public static final String AppCheckAccount      = URL + "wechat/usernameexist"; //校验用户名是否存在


    /**
     * SharedPreferences 时使用
     */
    public static final String IS_FIRSTSTART = "isFirstStart";						//是否首次启动
    /**
     * 设备屏幕宽度高度
     */
    public static int displayWidth = 0;				//屏幕宽度
    public static int displayHeight = 0;			//屏幕高度
}
