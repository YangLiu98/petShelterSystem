package com.example.demo.service;

import com.example.demo.dao.FeedDao;
import com.example.demo.entity.Feed;
import com.example.demo.util.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FeedServiceImpl implements FeedService{
    @Autowired
    FeedDao feedDao;

    @Override
    public ResponseEntity<String> insert(Feed feed) {
        int count = feedDao.insert(feed);
        if(count>0){
            return ResponseFactory.success("Success to insert");
        }
        return ResponseFactory.badRequest("Fail to insert");
    }

    @Override
    public ResponseEntity<Feed> getDetail(Feed feed) {
        Feed temp=feedDao.selectByFeedId(feed);
        if(temp!=null){
            return ResponseFactory.success(temp);
        }
        return ResponseFactory.notFound(null);
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> allMyFeed(Feed feed) {
        return ResponseFactory.success(feedDao.select(feed));
    }

    @Override
    public ResponseEntity<String> delete(Feed feed) {
        int count = feedDao.delete(feed);
        if(count>0){
            return ResponseFactory.success("Success to delete");
        }
        return ResponseFactory.badRequest("Fail to delete");
    }
}
