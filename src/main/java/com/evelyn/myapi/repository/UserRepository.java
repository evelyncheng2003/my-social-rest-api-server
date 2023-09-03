package com.evelyn.myapi.repository;

import com.evelyn.myapi.model.Comment;
import com.evelyn.myapi.model.Post;
import com.evelyn.myapi.model.User;

import java.util.List;

//@Repository
//public interface UserRepository extends CrudRepository<User, Long> {
    public interface UserRepository {

    long count();

    List<User> findAll();

    User findById(Long id);

    List<User> findUserByEmail(String email);

   int checkUserLogin(User user);

    int saveUser(User user);

    int updateUser(User user);

    int deletePost(Integer id);

    Post findPostById(Long id);

    // comments
    int saveComment(Comment comment);

    List<Comment> findAllComments();

    List<Comment> findCommentByPostId(Long id);

    int savePost(Post post);

    int updatePost(Post post);

    List<Post> findAllPosts();
}



