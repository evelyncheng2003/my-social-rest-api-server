package com.evelyn.myapi.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post_t")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="post_id")
    private Long post_id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="creator")
    private String creator;

    @Column(name="likes")
    private Long likes;

    @Column(name="post_time")
    private Long posttime;

    @Column(name="occasion")
    private String occasion;

    @Column(name="comments")
    private Long comments;

    public Post(Long post_id, String title, String description, String creator, Long likes, Long posttime, String occasion, Long comments) {
        this.post_id = post_id;
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.likes = likes;
        this.posttime = posttime;
        this.occasion = occasion;
        this.comments = comments;
    }


    public Long getPostId() {
        return post_id;
    }


    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getCreator() {
        return this.creator;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }
    public Long getLikes() {
        return this.likes;
    }

    public void setTime(Long posttime) {
        this.posttime = posttime;
    }
    public Long getTime() {
        return this.posttime;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }
    public String getOccasion() {
        return this.occasion;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }
    public Long getComments() {
        return this.comments;
    }
}
