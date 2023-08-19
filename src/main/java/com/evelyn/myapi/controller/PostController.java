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
public class PostController {

    @Autowired
    JdbcUserRepository jdbcUserRepository;

    // comment
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/api/post/create", method=RequestMethod.POST)
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        System.out.println(post.getTitle());
        System.out.println(post.getDescription());
        System.out.println(post.getCreator());
        System.out.println(post.getLikes());
        System.out.println(post.getTime());
        System.out.println(post.getOccasion());
        System.out.println(post.getComments());


        jdbcUserRepository.savePost(post);


        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value ="/api/post/likes", method=RequestMethod.POST)
    public ResponseEntity<Post> updateLikes(@RequestBody Post post) {
        System.out.println("Update number of likes");
        System.out.println("Likes: " + post.getLikes());

        jdbcUserRepository.updatePost(post);


        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/api/post/all", method=RequestMethod.GET)
    public List<Post> getAll() {
        List<Post> postList = jdbcUserRepository.findAllPosts();
        System.out.println(postList);
        for (Post c : postList) {
            System.out.println(c.getTime());
            System.out.println(c.getDescription());
        }
        return jdbcUserRepository.findAllPosts();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/api/post/find/{id}", method=RequestMethod.GET)
    public Post getPostById(@PathVariable(value = "id") Long id) {
        return jdbcUserRepository.findPostById(id);
    }



}
