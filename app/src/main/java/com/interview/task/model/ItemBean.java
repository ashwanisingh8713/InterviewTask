package com.interview.task.model;

/**
 * Created by ashwanisingh on 24/06/18.
 */
public class ItemBean {
    private String mImgUrl;
    private String mTag;


    public ItemBean(String mImgUrl, String mTag) {
        this.mImgUrl = mImgUrl;
        this.mTag = mTag;
    }

    public String getImgUrl() {
        return mImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.mImgUrl = imgUrl;
    }

    public String getTag() {
        return mTag;
    }

    public void setTitle(String title) {
        this.mTag = title;
    }

}
