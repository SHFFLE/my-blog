package com.codeup.dao;
import com.codeup.models.Post;
import com.codeup.models.Posts;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostsDao implements Posts {
    private Connection connection = null;

    public PostsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Post> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM posts");
            ResultSet rs = stmt.executeQuery();
            return createPostsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public int insert(Post post) {
        try {
            String insertQuery = "INSERT INTO posts(title, body) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getBody());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new post.", e);
        }
    }

    private Post extractPost(ResultSet rs) {
        try {
            return new Post(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("body")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error in extractPost", e);
        }
    }

    private List<Post> createPostsFromResults(ResultSet rs) throws SQLException {
        List<Post> posts = new ArrayList<>();
        while (rs.next()) {
            posts.add(extractPost(rs));
        }
        return posts;
    }
}
