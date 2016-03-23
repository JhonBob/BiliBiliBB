package com.bob.bilibilibb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asus on 2016/1/24.
 */
public class VideoDatabaseOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "BiliBili.db";
    public static final String TABLE_RECENT = "recent"; //最新视频表 用来缓存各分类下的最新视频
    public static final String TABLE_MAIN_HOT = "main_hot";//首页最热视频表 用来缓存首页最热视频和大分区下的推荐视频


    public VideoDatabaseOpenHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createRecentTable = "create table "+TABLE_RECENT+"(_id integer primary key autoincrement,aid text,copyright text,typeid integer,typename text,title text,subtitle text,play text,review text,videoReview text,favorites text,mid text,author text,description text,createTime text,pic text,credit text,coins text,duration text,comment text,badgepay text,category text)";
        String createMainHotTable = "create table "+TABLE_MAIN_HOT +"(_id integer primary key autoincrement,aid text,copyright text,typeid integer,typename text,title text,subtitle text,play text,review text,videoReview text,favorites text,mid text,author text,description text,createTime text,pic text,credit text,coins text,duration text,comment text,badgepay text,category text)";
        System.out.println(createRecentTable);
        System.out.println(createMainHotTable);
        db.execSQL(createRecentTable);
        db.execSQL(createMainHotTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
