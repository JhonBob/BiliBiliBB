package com.bob.bilibilibb.bean;

import java.util.ArrayList;

/**
 * Created by kaede31416 on 16-2-14.
 */
public class VideoDownloadUrl {
    private String order;
    private String length;
    private String size;
    private String url;//主要地址
    private ArrayList<String> backup_url;//一般都有两个后备地址

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<String> getBackup_url() {
        return backup_url;
    }

    public void setBackup_url(ArrayList<String> backup_url) {
        this.backup_url = backup_url;
    }

    @Override
    public String toString() {
        return "VideoDownloadUrl{" +
                "order='" + order + '\'' +
                ", length='" + length + '\'' +
                ", size='" + size + '\'' +
                ", url='" + url + '\'' +
                ", backup_url=" + backup_url +
                '}';
    }
}
