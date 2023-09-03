package com.evelyn.myapi.jdbcrepository;

import com.evelyn.myapi.model.Comment;
import com.evelyn.myapi.model.Post;
import com.evelyn.myapi.model.User;
import com.evelyn.myapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Service
//public class UserService {

public class JdbcUserRepository implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("select count(*) from social.userinfo_t", Long.class);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query (
                "select * from social.userinfo_t",
                (rs, rowNum) -> new User(rs.getLong("user_id"),
                                        rs.getString("email"),
                                        rs.getString("user_password"),
                                        rs.getString("phone_number")
                                )
            );
    }

    @Override
    public User findById(Long userId) {
        return jdbcTemplate.queryForObject (
                "select * from social.userinfo_t where user_id = ?",
                new Object[] {userId},
                (rs, rowNum) -> new User(rs.getLong("user_id"),
                                        rs.getString("email"),
                                        rs.getString("user_password"),
                                        rs.getString("phone_number")
                )
        );

    }

    @Override
    public List<User> findUserByEmail(String email) {
        return jdbcTemplate.query (
                "select * from social.userinfo_t where email = ?",
                new Object[] {email},
                (rs, rowNum) -> new User(rs.getLong("user_id"),
                        rs.getString("email"),
                        rs.getString("user_password"),
                        rs.getString("phone_number")
                )
        );

    }

    @Override
    public int saveUser(User user) {
        return jdbcTemplate.update(
                "insert into social.userinfo_t(email, user_password, phone_number) values (?,?,?)",
                user.getEmail(), user.getPassword(), user.getPhoneNumber()
        );
    }
    @Override
    public int checkUserLogin(User user) {
        /*
        String sql = "insert into social.userinfo_t(email, user_password, nick_name) values (?,?,?) " +
                "ON CONFLICT (email)" +
                "DO UPDATE set email = social.userinfo_t.email" +
                "RETURNING user_password";
        return jdbcTemplate.update( sql,
                user.getEmail(), user.getPassword(), user.getNickname()
        );
         */
        return 0;
    }

    @Override
    public int updateUser(User user) {
        System.out.println(user.getUserid());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getPhoneNumber());
        return jdbcTemplate.update(
                "update social.userinfo_t set email = ?, user_password = ?, phone_number = ? where user_id = ?",
                user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.getUserid()
        );
    }

    @Override
    public int saveComment(Comment comment) {
        return jdbcTemplate.update(
                "insert into social.comment_t(user_name, user_comment, comment_time, post_id) values (?, ?, ?, ?)",
                comment.getUsername(), comment.getUsercomment(), comment.getCommenttime(), comment.getPostId()
        );
    }

    @Override
    public List<Comment> findAllComments() {
        return jdbcTemplate.query (
                "select * from social.comment_t",
                (rs, rowNum) -> new Comment(rs.getLong("comment_id"),
                        rs.getString("user_name"),
                        rs.getString("user_comment"),
                        rs.getLong("comment_time"),
                        rs.getLong("post_id")
                )
        );
    }

    @Override
    public List<Comment> findCommentByPostId(Long postId) {
        return jdbcTemplate.query (
                "select * from social.comment_t where post_id = ?",
                new Object[] {postId},
                (rs, rowNum) -> new Comment(rs.getLong("comment_id"),
                        rs.getString("user_name"),
                        rs.getString("user_comment"),
                        rs.getLong("comment_time"),
                        rs.getLong("post_id")
                )
        );
    }

    @Override
    public int savePost(Post post) {
        System.out.println("savePost called");
        return jdbcTemplate.update(
                "insert into social.post_t(title, description, creator, likes, post_time, occasion) values (?, ?, ?, ?, ?,?)",
               post.getTitle(), post.getDescription(), post.getCreator(), post.getLikes(), post.getTime(), post.getOccasion()


        );
    }

    @Override
    public int updatePost(Post post) {
        System.out.println("updatePost called");
        System.out.println("post Id is: " + post.getPostId());
        return jdbcTemplate.update(
                "update social.post_t SET likes = ? WHERE post_id = ?",
                 post.getLikes(), post.getPostId()
        );
    }

    @Override
    public List<Post> findAllPosts() {
        return jdbcTemplate.query (
                "select * from social.post_t",
                (rs, rowNum) -> new Post(rs.getLong("post_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("creator"),
                        rs.getLong("likes"),
                        rs.getLong("post_time"),
                        rs.getString("occasion"),
                        rs.getLong("comments")
                        )
        );
    }

    @Override
    public int deletePost(Integer id) {
        System.out.println("deletePost called");
        System.out.println("######### post_id is: " + id);
        return jdbcTemplate.update(
                "DELETE from social.post_t where post_id = ?",
                id
        );
    }


    @Override
    public Post findPostById(Long postId) {
        return jdbcTemplate.queryForObject (
                "select * from social.post_t where post_id = ?",
                new Object[] {postId},
                (rs, rowNum) -> new Post(rs.getLong("post_Id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("creator"),
                        rs.getLong("likes"),
                        rs.getLong("post_time"),
                        rs.getString("occasion"),
                        rs.getLong("comments")
                )
        );

    }

    /*
    @Autowired(required = true)
    UserRepository usrRepository;

    // CREATE
    public User createUser(User usr) {
        return usrRepository.save(usr);
    }

    // READ
    public List<User> getEmployees() {
        return (List<User>) usrRepository.findAll();
    }

    // DELETE
    public void deleteUser(Long usrId) {
        usrRepository.deleteById(usrId);
    }

    // UPDATE
    public User updateUser(Long usrId, User userDetails) {
        User usr = usrRepository.findById(usrId).get();
        usr.setFirstName(userDetails.getFirstName());
        usr.setLastName(userDetails.getLastName());
        usr.setEmail(userDetails.getEmail());

        return usrRepository.save(usr);
    }

     */
}