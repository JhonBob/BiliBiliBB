package com.bob.bilibilibb.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bob.bilibilibb.bean.VideoDetail;
import com.bob.bilibilibb.bean.VideoListResult;
import com.bob.bilibilibb.utils.Constant;


import java.util.ArrayList;
import java.util.Map;

/**
 * Created by asus on 2016/1/24.
 *
 */
public class VideoDao {
    private VideoDatabaseOpenHelper instance;
    private Context mContext;
    public static final int TYPE_RECENT = 1;
    public static final int TYPE_MAIN_HOT = 2;

    public static final int FLAG_RANDOM = 100;
    public static final int FLAG_TOP_FOUR_TABLE_HOT = 101;
    public static final int FLAG_TOP_FOUR_TABLE_RECENT = 102;
    public static final int FLAG_LAST_BUT_TOP_FOUR = 103;


    public VideoDao(Context context){
        mContext = context;
        if(instance == null){
            instance = new VideoDatabaseOpenHelper(mContext);
        }
    }

    /**
     * 将网络查询到的结果插入到缓存表中 缓存表只缓存一定数量的数据
     * @param result 要插入的结果集
     * @param tableType VideoDao.TYPE_RECENT 和 VideoDao.TYPE_HOT中的一种
     */
    public void insertVideo(VideoListResult result,int tableType,int videoType){
        deleteAll(tableType,videoType);//插入更新之前 需要先删除上次查询的结果
        Map<String, VideoDetail> list = result.getList();
        int count = list.size();
        SQLiteDatabase db = instance.getWritableDatabase();
        db.beginTransaction(); //开启事务以优化插入性能
        try{
            for(int i=0;i<count;i++){
                VideoDetail videoDetail = list.get(i + "");
                ContentValues values = new ContentValues();
                values.put("aid", videoDetail.getAid());
                values.put("copyright", videoDetail.getCopyright());
                values.put("typeid", videoDetail.getTypeid());
                values.put("typename", videoDetail.getTypename());
                values.put("title", videoDetail.getTitle());
                values.put("subtitle", videoDetail.getSubtitle());
                values.put("play", videoDetail.getPlay());
                values.put("review", videoDetail.getReview());
                values.put("videoReview", videoDetail.getVideoReview());
                values.put("favorites", videoDetail.getFavorites());
                values.put("mid", videoDetail.getMid());
                values.put("author", videoDetail.getAuthor());
                values.put("description", videoDetail.getDescription());
                values.put("createTime", videoDetail.getCreateTime());
                values.put("pic", videoDetail.getPic());
                values.put("credit", videoDetail.getCredit());
                values.put("coins", videoDetail.getCoins());
                values.put("duration", videoDetail.getDuration());
                values.put("comment", videoDetail.getComment());
                values.put("badgepay", videoDetail.getBadgepay());
                values.put("category",getCategory(videoType));
                String tableName = getTableName(tableType);
                db.insert(tableName,null,values);
            }
            db.setTransactionSuccessful();
        }catch (NullPointerException e){ //抓一下空指针异常
            System.out.println(list.toString());
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }

    public ArrayList<VideoDetail> queryVideoByCategory(int tableType,int category){
        return queryVideoByCategory(tableType,category,0);
    }

    /**
     * 根据目录查找视频 首页专用
     * @param tableType 表类型
     * @param category 目录
     * @param flag 首页专用
     * @return 结果集
     */
    public ArrayList<VideoDetail> queryVideoByCategory(int tableType,int category,int flag){
        ArrayList<VideoDetail> list = new ArrayList<>();
        SQLiteDatabase db = instance.getReadableDatabase();

        StringBuilder sb = new StringBuilder("select * from ");
        sb.append(getTableName(tableType));
        if(category != Constant.RE_MEN_TUI_JIAN){//热门推荐只需要从热门表中随机挑选出4条即可
            sb.append(" where category = ?");
        }
        if(flag == FLAG_RANDOM){
            sb.append(" order by RANDOM() limit 4");
        }else if(flag == FLAG_TOP_FOUR_TABLE_HOT){
            sb.append(" order by play desc limit 4");
        }else if(flag == FLAG_TOP_FOUR_TABLE_RECENT){
            sb.append(" order by play asc limit 4");
        }else if(flag == FLAG_LAST_BUT_TOP_FOUR){
            sb.append(" order by play asc limit 100 offset 4");
        }else if(flag != 0 ){
            throw new IllegalArgumentException("flag can not be "+flag);
        }
        String sql = sb.toString();
        Cursor cursor;
        if(category != Constant.RE_MEN_TUI_JIAN) {
            cursor = db.rawQuery(sql, new String[]{getCategory(category) + ""});
        }else {
            cursor = db.rawQuery(sql,null);
        }
        while (cursor.moveToNext()){
            VideoDetail detail = new VideoDetail();
            detail.setAid(cursor.getString(1));
            detail.setCopyright(cursor.getString(2));
            detail.setTypeid(cursor.getInt(3));
            detail.setTypename(cursor.getString(4));
            detail.setTitle(cursor.getString(5));
            detail.setSubtitle(cursor.getString(6));
            detail.setPlay(cursor.getString(7));
            detail.setReview(cursor.getString(8));
            detail.setVideoReview(cursor.getString(9));
            detail.setFavorites(cursor.getString(10));
            detail.setMid(cursor.getString(11));
            detail.setAuthor(cursor.getString(12));
            detail.setDescription(cursor.getString(13));
            detail.setCreateTime(cursor.getString(14));
            detail.setPic(cursor.getString(15));
            detail.setCredit(cursor.getString(16));
            detail.setCoins(cursor.getString(17));
            detail.setDuration(cursor.getString(18));
            detail.setComment(cursor.getString(19));
            detail.setBadgepay(cursor.getString(20));
            list.add(detail);
        }
        cursor.close();

        return list;
    }

    public ArrayList<VideoDetail> queryVideo(int tableType,int videoType) {
        return queryVideoByType(tableType,videoType,0);
    }

    /**
     *
     * @param tableType 表的类型
     * @param videoType 视频的类型
     * @param flag 首页刷新新动态使用 0表示不使用flag
     * @return 结果集
     */
    public ArrayList<VideoDetail> queryVideoByType(int tableType,int videoType,int flag){
        ArrayList<VideoDetail> list = new ArrayList<>();
        SQLiteDatabase db = instance.getReadableDatabase();

        StringBuilder sb = new StringBuilder("select * from ");
        sb.append(getTableName(tableType));
        sb.append("where videoType = ?");
        if(flag == FLAG_RANDOM){
            sb.append(" order by RANDOM() limit 4");
        }if(flag == FLAG_RANDOM){
            sb.append(" order by RANDOM() limit 4");
        }else if(flag == FLAG_TOP_FOUR_TABLE_HOT){
            sb.append(" order by play desc limit 4");
        }else if(flag == FLAG_TOP_FOUR_TABLE_RECENT){
            sb.append(" order by play asc limit 4");
        }else if(flag != 0 ){
            throw new IllegalArgumentException("flag can not be "+flag);
        }

        String sql = sb.toString();
        Cursor cursor = db.rawQuery(sql, new String[]{videoType + ""});

        while (cursor.moveToNext()){
            VideoDetail detail = new VideoDetail();
            detail.setAid(cursor.getString(1));
            detail.setCopyright(cursor.getString(2));
            detail.setTypeid(cursor.getInt(3));
            detail.setTypename(cursor.getString(4));
            detail.setTitle(cursor.getString(5));
            detail.setSubtitle(cursor.getString(6));
            detail.setPlay(cursor.getString(7));
            detail.setReview(cursor.getString(8));
            detail.setVideoReview(cursor.getString(9));
            detail.setFavorites(cursor.getString(10));
            detail.setMid(cursor.getString(11));
            detail.setAuthor(cursor.getString(12));
            detail.setDescription(cursor.getString(13));
            detail.setCreateTime(cursor.getString(14));
            detail.setPic(cursor.getString(15));
            detail.setCredit(cursor.getString(16));
            detail.setCoins(cursor.getString(17));
            detail.setDuration(cursor.getString(18));
            detail.setComment(cursor.getString(19));
            detail.setBadgepay(cursor.getString(20));
            list.add(detail);
        }
        cursor.close();

        return list;
    }

    public void deleteAll(int tableType,int videoType){
        SQLiteDatabase db = instance.getWritableDatabase();
        int category = getCategory(videoType);
        if(category == videoType){//说明插入进来的是一个category的数据
            db.delete(getTableName(tableType),"category = ?",new String[]{category+""});
        }else {
            db.delete(getTableName(tableType), "typeid = ?", new String[]{videoType + ""});
        }
    }

    private String getTableName(int tableType){
        String tableName;
        if(tableType == TYPE_RECENT){
            tableName = VideoDatabaseOpenHelper.TABLE_RECENT;
        }else if(tableType == TYPE_MAIN_HOT){
            tableName = VideoDatabaseOpenHelper.TABLE_MAIN_HOT;
        }else {
            throw new IllegalArgumentException("tableType can not be "+tableType);
        }
        return tableName;
    }

    private int getCategory(int videoType){
        switch (videoType){
            case Constant.FAN_JU:
            case Constant.WAN_JIE_DONG_HUA:
            case Constant.LIAN_ZAI_DONG_HUA:
            case Constant.GUO_CHAN_DONG_HUA:
            case Constant.ZI_XUN:
            case Constant.GUAN_FANG_YAN_SHEN:
                return Constant.FAN_JU;
            case Constant.DONG_HUA:
            case Constant.MAD_AMV:
            case Constant.MMD_3D:
            case Constant.DUANPIAN_SHOUSHU_PEIHE:
                return Constant.DONG_HUA;
            case Constant.YIN_YUE:
            case Constant.FAN_CHANG:
            case Constant.VOCALOID_UTAU:
            case Constant.YAN_ZOU:
            case Constant.OP_ED_OST:
            case Constant.TONG_REN_YIN_YUE:
            case Constant.SAN_CI_YUAN_YIN_YUE:
            case Constant.YIN_YUE_XUAN_JI:
                return Constant.YIN_YUE;
            case Constant.WU_DAO:
            case Constant.ZHAI_WU:
            case Constant.SAN_CI_YUAN_WU_DAO:
                return Constant.WU_DAO;
            case Constant.YOU_XI:
            case Constant.DAN_JI_LIAN_JI:
            case Constant.WANG_YOU_DIAN_JING:
            case Constant.YIN_YOU:
            case Constant.MUGEN:
            case Constant.GMV:
                return Constant.YOU_XI;
            case Constant.KE_JI:
            case Constant.KE_JI_REN_WEN:
            case Constant.YE_SHENG_JI_SHU_XIE_HUI:
            case Constant.QU_WEI_DUAN_PIAN:
            case Constant.GONG_KAI_KE:
            case Constant.JI_XIE:
                return Constant.KE_JI;
            case Constant.YU_LE:
            case Constant.GAO_XIAO:
            case Constant.SHENG_HUO:
            case Constant.ZONG_YI:
                return Constant.YU_LE;
            case Constant.GUI_CHU:
            case Constant.GUI_CHU_TIAO_JIAO:
            case Constant.YIN_MAD:
            case Constant.REN_LI_VOCALOID:
                return Constant.GUI_CHU;
            default:
                Log.e("json",videoType+"");
                throw new IllegalArgumentException("videoType can not be "+videoType);
        }
    }

}
