package com.bob.bilibilibb.utils;

/**
 * Created by asus on 2016/1/24.
 */
public interface Constant {
    String appkey = "876fe0ebd0e67a0f";
    String newAppkey = "85eb6835b0a1034e";//搜索功能需要新版appKey配合appSecret使用
    String newAppSecret = "2ad42749773c441109bdc0191257a664";
    String BASE_URL = "http://api.bilibili.cn/";
    String BASE_URL_VIDEO = "http://interface.bilibili.com/";//获取Video下载地址的API
    String BASE_URL_DANMA="http://comment.bilibili.cn/";//获取弹幕XML文件的API

    //PagerView
    int PAGER_VIEW = -1;

    //热门推荐项
    int RE_MEN_TUI_JIAN = 0;

    //搜索结果返回的类型
    String TYPE_SPEC = "special";
    String TYPE_VIDEO = "video";


    //番剧
    int FAN_JU = 13;
    int WAN_JIE_DONG_HUA = 32;//完结动画
    int LIAN_ZAI_DONG_HUA = 33; //连载动画
    int GUO_CHAN_DONG_HUA = 153;//国产动画
    int ZI_XUN = 51;//资讯
    int GUAN_FANG_YAN_SHEN = 152; //官方延伸

    //动画
    int DONG_HUA = 1;
    int MAD_AMV = 24; //MAD·AMV
    int MMD_3D = 25 ;//MMD·3D
    int DUANPIAN_SHOUSHU_PEIHE = 47;//短片·手书·配合


    //音乐
    int YIN_YUE = 3;
    int FAN_CHANG = 31;//翻唱
    int VOCALOID_UTAU = 30;
    int VOCALOID = 56;
    int UTAU = 57;
    int YAN_ZOU = 59;//演奏
    int OP_ED_OST = 54;
    int TONG_REN_YIN_YUE = 28;//同人音乐
    int SAN_CI_YUAN_YIN_YUE = 29;//三次元音乐
    int YIN_YUE_XUAN_JI = 130; //音乐选集

    //舞蹈
    int WU_DAO = 129;
    int ZHAI_WU = 20;//宅舞
    int SAN_CI_YUAN_WU_DAO = 154;//三次元舞蹈

    //游戏
    int YOU_XI = 4;
    int DAN_JI_LIAN_JI = 17;//单机联机
    int WANG_YOU_DIAN_JING = 65;//网游电竞
    int YIN_YOU = 136;//音游
    int MUGEN = 19;
    int GMV = 121;

    //科技
    int KE_JI = 36;
    int KE_JI_REN_WEN = 107;//科技人文
    int QU_WEI_DUAN_PIAN = 108;//趣味短片
    int YE_SHENG_JI_SHU_XIE_HUI = 122;//野生技术协会
    int GONG_KAI_KE = 105; //公开课
    int JI_XIE = 68;//机械

    //娱乐
    int YU_LE = 5;
    int GAO_XIAO = 138;//搞笑
    int SHENG_HUO = 21;//生活
    int ZONG_YI = 71;//综艺

    //鬼畜
    int GUI_CHU = 119;
    int GUI_CHU_TIAO_JIAO = 22;//鬼畜调教
    int YIN_MAD = 26;//音MAD
    int REN_LI_VOCALOID = 126;//人力VOCALOID

    //电影
    int DIAN_YING = 23;
    int DIAN_YING_XIANG_GUAN = 82;//电影相关

    //电视剧
    int DIAN_SHI_JU = 11;
    int LIAN_ZAI_JU_JI = 15;//连载剧集




}
