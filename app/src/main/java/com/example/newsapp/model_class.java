package com.example.newsapp;

public class model_class {

    private String title;
    private String source;
    private String link;
    private String pubDate;
    private String image_url;

    public model_class(String title, String link, String pubDate, String image_url) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.image_url = image_url;
    }

    public model_class() {
    }

    public model_class(String title, String source, String link, String pubDate, String image_url) {
        this.title = title;
        this.source = source;
        this.link = link;
        this.pubDate = pubDate;
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
