package com.bob.bilibilibb.bean;

import java.util.ArrayList;

/**
 * Created by kaede31416 on 16-2-14.
 */
public class Video {
    private String result;
    private String timelength;
    private String format;
    private String accept_format;
    private String accept_quality;
    private String from;
    private String seek_param;
    private String seek_type;
    private String src;
    private ArrayList<VideoDownloadUrl> urlList;//有可能包含多个视频

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTimelength() {
        return timelength;
    }

    public void setTimelength(String timelength) {
        this.timelength = timelength;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAccept_format() {
        return accept_format;
    }

    public void setAccept_format(String accept_format) {
        this.accept_format = accept_format;
    }

    public String getAccept_quality() {
        return accept_quality;
    }

    public void setAccept_quality(String accept_quality) {
        this.accept_quality = accept_quality;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSeek_param() {
        return seek_param;
    }

    public void setSeek_param(String seek_param) {
        this.seek_param = seek_param;
    }

    public String getSeek_type() {
        return seek_type;
    }

    public void setSeek_type(String seek_type) {
        this.seek_type = seek_type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public ArrayList<VideoDownloadUrl> getUrlList() {
        return urlList;
    }

    public void setUrlList(ArrayList<VideoDownloadUrl> urlList) {
        this.urlList = urlList;
    }

    @Override
    public String toString() {
        return "Video{" +
                "result='" + result + '\'' +
                ", timelength='" + timelength + '\'' +
                ", format='" + format + '\'' +
                ", accept_format='" + accept_format + '\'' +
                ", accept_quality='" + accept_quality + '\'' +
                ", from='" + from + '\'' +
                ", seek_param='" + seek_param + '\'' +
                ", seek_type='" + seek_type + '\'' +
                ", src='" + src + '\'' +
                ", urlList=" + urlList +
                '}';
    }
}
