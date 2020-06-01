package com.example.demo.service;

import com.example.demo.entity.Feed;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
public interface FeedService {
    public ResponseEntity<String> insert(Feed feed);

    public ResponseEntity<Feed> getDetail(Feed feed);

    public ResponseEntity<List<Map<String, Object>>> allMyFeed(Feed feed);

    public ResponseEntity<String> delete(Feed feed);

    public ResponseEntity<String> update(Feed feed);

    public ResponseEntity<Integer> deleteAdmin(Feed feed);
}
