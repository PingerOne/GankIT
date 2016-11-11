package com.pinger.gankit.model.bean;

/*
 *  @项目名：  GankIT 
 *  @包名：    com.pinger.gankit.model.bean
 *  @文件名:   NewsBean
 *  @创建者:   Pinger
 *  @创建时间:  2016/11/11 16:51
 *  @描述：    TODO
 */

import java.util.List;

public class NewsBean {


    public PageBean pagebean;
    public int ret_code;

    public static class PageBean {

        public int allNum;
        public int allPages;
        public int currentPage;
        public int maxResult;
        public List<ContentList> contentlist;
    }

    public static class ContentList {

        public String channelId;
        public String channelName;
        public String content;
        public String desc;
        public String html;
        public String link;
        public String nid;
        public String pubDate;
        public int sentiment_display;
        public String source;
        public String title;
        public List<NewsImage> imageurls;
    }

    public static class NewsImage {

        public int width;
        public int height;
        public String url;
    }

}
