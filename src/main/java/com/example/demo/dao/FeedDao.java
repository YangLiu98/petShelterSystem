package com.example.demo.dao;

import com.example.demo.entity.Feed;

import java.util.List;
import java.util.Map;

public interface FeedDao {
    public int insert(Feed feed);

    public Feed selectByFeedId(Feed feed);

    public Feed selectByUserId(Feed feed);

    public List<Map<String,Object>> select(Feed feed);

    public int delete(Feed feed);

    public int update(Feed feed);

    public int deleteAdmin(Feed feed);
}
