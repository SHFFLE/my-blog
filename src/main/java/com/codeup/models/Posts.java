package com.codeup.models;

import java.util.List;

public interface Posts {
    List<Post> all();
    int insert(Post post);
}
