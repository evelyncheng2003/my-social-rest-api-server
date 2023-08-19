package com.evelyn.myapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment_t")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="comment_id")
    private Long comment_id;

    @Column(name="user_name")
    private String username;

    @Column(name="user_comment")
    private String usercomment;

    @Column(name="comment_time")
    private Long commenttime;

    @Column(name="post_id")
    private Long postid;

    public Comment(Long post_id, String username, String comment, Long time, Long postid) {
        this.comment_id = comment_id;
        this.username = username;
        this.usercomment = comment;
        this.commenttime = time;
        this.postid = postid;

    }


    public Long getCommentId() {
        return comment_id;
    }


    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsercomment(String comment) {
        this.usercomment = comment;
    }
    public String getUsercomment() {
        return this.usercomment;
    }
    public void setCommenttime(Long time) {
        this.commenttime = time;
    }
    public Long getCommenttime() {
        return this.commenttime;
    }

    public void setPostId(Long postid) {
        this.postid = postid;
    }
    public Long getPostId() {
        return this.postid;
    }
}
