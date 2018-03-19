package com.example.yuki.yukitv.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 项目：YukiTV
 * 作者：Yuki - 2017/6/5
 * 邮箱：125508663@qq.com
 */

public  class UIutil {
	public static int dp2px(Context context, int dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	public static int px2dp(Context context, int pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	public static DisplayMetrics getDisplayMetrics(Context context){
		return context.getResources().getDisplayMetrics();
	}
}


