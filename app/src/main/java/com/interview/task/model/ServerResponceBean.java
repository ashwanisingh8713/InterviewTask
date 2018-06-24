package com.interview.task.model;

import java.util.List;

/**
 * Created by ashwanisingh on 25/06/18.
 */
public class ServerResponceBean {


    private List<HitsBean> hits;

    public List<HitsBean> getHits() {
        return hits;
    }

    public static class HitsBean {


        private String tags;
        private String previewURL;

        public String getTags() {
            return tags;
        }

        public String getPreviewURL() {
            return previewURL;
        }

    }
}
