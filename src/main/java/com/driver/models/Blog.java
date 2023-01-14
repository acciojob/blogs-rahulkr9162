package com.driver.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "blog_table")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String content;

    private Date pubDate;

    @ManyToOne
    @JoinColumn
    private User user_Info;

    @OneToMany(mappedBy = "blog_Info", cascade = CascadeType.ALL)
    private List<Image> imageList;

    public Blog(String title, String content, Date pubDate, User user_Info, List<Image> imageList) {
        this.title = title;
        this.content = content;
        this.pubDate = pubDate;
        this.user_Info = user_Info;
        this.imageList = imageList;
    }

    public Blog() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public User getUser_Info() {
        return user_Info;
    }

    public void setUser_Info(User user_Info) {
        this.user_Info = user_Info;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}