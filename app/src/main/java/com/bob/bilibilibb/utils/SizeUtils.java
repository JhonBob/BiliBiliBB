package com.bob.bilibilibb.utils;

import android.content.Context;

/**
 * Created by asus on 2016/2/4.
 */
public class SizeUtils {
    public static int px2dp(Context context, int px){
        float density = context.getResources().getDisplayMetrics().density;
        return (int) ((px/density)+0.5f);
    }

    public static int dp2px(Context context,double dp){
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp*density + 0.5f);
    }
}
