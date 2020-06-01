package com.example.demo.dao;

import com.example.demo.entity.Feed;
import com.example.demo.entity.Find;

import java.util.List;
import java.util.Map;

public interface FindDao {
    public int insert(Find find);

    public Find selectByFindId(Find find);

    public Find selectByUserId(Find find);

    public List<Map<String,Object>> select(Find find);

    public int delete(Find find);

    public int update(Find find);

    public int deleteAdmin(Find find);
}
