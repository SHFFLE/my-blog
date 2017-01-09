package com.codeup.models;

import java.util.List;

public interface Posts {
    List<Post> all();
    void insert(Post post);
    Post getPostByID(int id);
    void deletePost(int id);
    void updatePost(Post post);
}
