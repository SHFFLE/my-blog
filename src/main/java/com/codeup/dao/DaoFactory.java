package com.codeup.dao;

import com.codeup.models.Posts;

public class DaoFactory {
    private static Posts postsDao;
    private static Config config = new Config();

    public static Posts getPostsDao() {
        if (postsDao == null) {
            postsDao = new PostsDao(config);
        }
        return postsDao;
    }
}