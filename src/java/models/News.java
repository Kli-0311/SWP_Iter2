/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


import java.util.Date;

/**
 *
 * @author khanh
 */
public class News {
    private int newsId;
    private Date   publishedDate;
    private String imageUrl;
    private String title;
    private String content;
    private String  userId;

    public News() {
    }

    public News(int newsId, Date publishedDate, String imageUrl, String title, String content, String userId) {
        this.newsId = newsId;
        this.publishedDate = publishedDate;
        this.imageUrl = imageUrl;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "News{" + "newsId=" + newsId + ", publishedDate=" + publishedDate + ", imageUrl=" + imageUrl + ", title=" + title + ", content=" + content + ", userId=" + userId + '}';
    }

   
}
