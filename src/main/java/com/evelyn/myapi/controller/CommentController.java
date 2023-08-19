package com.evelyn.myapi.controller;

import com.evelyn.myapi.jdbcrepository.JdbcUserRepository;
import com.evelyn.myapi.model.Comment;
import com.evelyn.myapi.model.Post;
import com.evelyn.myapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    JdbcUserRepository jdbcUserRepository;
    // comment
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/comment/create", method=RequestMethod.POST)
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        System.out.println(comment.getUsername());
        System.out.println(comment.getUsercomment());

        jdbcUserRepository.saveComment(comment);


        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/api/comment/all", method=RequestMethod.GET)
    public List<Comment> getAll() {
        List<Comment> commentList = jdbcUserRepository.findAllComments();
        System.out.println(commentList);
        for (Comment c : commentList) {
            System.out.println(c.getUsername());
            System.out.println(c.getUsercomment());
        }
        return jdbcUserRepository.findAllComments();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/api/comment/find/{id}", method=RequestMethod.GET)
    public List<Comment> getCommentsById(@PathVariable(value = "id") Long id) {
        return jdbcUserRepository.findCommentByPostId(id);
    }
}
