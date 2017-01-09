package com.codeup.dao;
import com.codeup.models.Post;
import com.codeup.models.Posts;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostsDao implements Posts {
    private Session session;

    public PostsDao(Session session) {
        this.session = session;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> all() {
        Query query = session.createQuery("from Post order by id desc");
        return query.list();

    }

    @Override
    public void insert(Post post) {
        Transaction tx = session.beginTransaction();
        session.save(post);
        tx.commit();
    }

    @Override
    public Post getPostByID(int id){
        Query query = session.createQuery("from Post where id = :id");
        query.setParameter("id", id);
        return (Post) query.uniqueResult();
    }

    @Override
    public void deletePost(int id){
        Transaction tx = session.beginTransaction();
        Post post = getPostByID(id);
        session.delete(post);
        tx.commit();
    }

    @Override
    public void updatePost(Post post){
        Transaction tx = session.beginTransaction();
        session.update(post);
        tx.commit();
    }

//    private Post extractPost(ResultSet rs) {
//        try {
//            return new Post(
//                    rs.getInt("id"),
//                    rs.getString("title"),
//                    rs.getString("body")
//            );
//        } catch (SQLException e) {
//            throw new RuntimeException("Error in extractPost", e);
//        }
//    }
//
//    private List<Post> createPostsFromResults(ResultSet rs) throws SQLException {
//        List<Post> posts = new ArrayList<>();
//        while (rs.next()) {
//            posts.add(extractPost(rs));
//        }
//        return posts;
//    }
}
