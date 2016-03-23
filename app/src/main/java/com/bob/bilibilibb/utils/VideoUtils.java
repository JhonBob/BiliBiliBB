package com.bob.bilibilibb.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

/**
 * Created by asus on 2016/2/5.
 */
public class VideoUtils {

    public static int getVideoTypeByName(String videoTypeName){
        switch (videoTypeName){
            case "番剧":
                return Constant.FAN_JU;
            case "连载动画":
                return Constant.LIAN_ZAI_DONG_HUA;
            case "完结动画":
                return Constant.WAN_JIE_DONG_HUA;
            case "国产动画":
                return Constant.GUO_CHAN_DONG_HUA;
            case "资讯":
                return Constant.ZI_XUN;
            case "官方延伸":
                return Constant.GUAN_FANG_YAN_SHEN;
            case "动画":
                return 1;
            case "音乐":
                return 3;
            case "舞蹈":
                return 129;
            case "游戏":
                return 4;
            case "科技":
                return 36;
            case "娱乐":
                return 5;
            case "鬼畜":
                return 119;
            case "电影":
                return 23;
            case "电视剧":
                return 11;
            default:
                return -1;
        }
    }
    
    public static String getStringVideoDetailCount(String countStr){
        try {
            int count = Integer.parseInt(countStr);
            if (count < 10000) {
                return countStr;
            } else {
                int wan = count / 10000;
                int qian = count % 10000 / 1000;
                return wan + "." + qian + "万";
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
            return "-";
        }
    }

    public static String getStringVideoIsEnd(String isEnd){
        if(isEnd.equals("1")){
            return "全集";
        }else {
            return "连载";
        }
    }

    public static String getDeliverTimeDisplay(String time){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year,month,day,0,0,0);
        long todayBeginTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        long yesterdayTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        long dayBeforeYesterdayTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        long dayBeforeBeforeTime = calendar.getTimeInMillis();
        long replyTime;
        try {
            replyTime = Long.parseLong(time)*1000;
        }catch (NumberFormatException e){
            e.printStackTrace();
            return "未知";
        }
        long currentTime = System.currentTimeMillis();
        String result;
        if(replyTime - todayBeginTime>0){//今天发送的消息
            if(currentTime-replyTime<3600000){//一小时内发送
                result = (currentTime-replyTime)/1000/60+"分钟前投递";
            }else {
                result = (currentTime-replyTime)/1000/60/60+"小时前投递";
            }
        }else if(replyTime - yesterdayTime>0){
            result = "一天前投递";
        }else if(replyTime - dayBeforeYesterdayTime>0){
            result = "两天前投递";
        }else if(replyTime - dayBeforeBeforeTime>0){
            result = "三天前投递";
        }else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日投递", Locale.CHINA);
            result = format.format(new Date(replyTime));
        }
        return result;
    }

    public static String getStringTimeDisplay(String time){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year,month,day,0,0,0);
        long todayBeginTime = calendar.getTimeInMillis();
        long replyTime;
        try {
            replyTime = Long.parseLong(time)*1000;
        }catch (NumberFormatException e){
            e.printStackTrace();
            replyTime = 0;
        }
        long timeOffset = replyTime - todayBeginTime;
        long currentTime = System.currentTimeMillis();
        String result;
        if(timeOffset>0){//今天发送的消息
            if(currentTime-replyTime<3600000){//一小时内发送
                result = (currentTime-replyTime)/1000/60+"分钟前";
            }else {
                result = (currentTime-replyTime)/1000/60/60+"小时前";
            }
        }else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
            result = format.format(new Date(replyTime));
        }
        return result;
    }

    public static String getSign(HashMap<String,String> params){//appKey也要手动加入进去
        Set<String> keys = params.keySet();
        ArrayList<String> keyList = new ArrayList<>(keys);
        String[] arr = keyList.toArray(new String[keyList.size()]);
        Arrays.sort(arr);
        String result = "";
        for (String key : arr) {
            String value = params.get(key);
            if(key .equals("keyword")){
                value = encodeValue(value);
            }
            result += (result.equals("")? "" : "&") + key + "=" + value;
        }
        Log.i("json",result);
        return md5Encryption(result+ Constant.newAppSecret);
    }

    public static String encodeValue(String keyword) {
        try {
            return URLEncoder.encode(keyword, "UTF-8").toUpperCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String md5Encryption(String source){
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] bs = digest.digest(source.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b:bs){
                int val = b & 0xff;
                if(val<16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
