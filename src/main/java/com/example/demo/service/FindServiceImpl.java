package com.example.demo.service;

import com.example.demo.dao.FindDao;
import com.example.demo.entity.Find;
import com.example.demo.util.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FindServiceImpl implements FindService {
    @Autowired
    FindDao findDao;

    @Override
    public ResponseEntity<String> insert(Find find) {
        int count = findDao.insert(find);
        if(count>0){
            return ResponseFactory.success("Success to insert");
        }
        return ResponseFactory.badRequest("Fail to insert");
    }

    @Override
    public ResponseEntity<Find> getDetail(Find find) {
        Find temp=findDao.selectByFindId(find);
        if(temp!=null){
            return ResponseFactory.success(temp);
        }
        return ResponseFactory.notFound(null);
    }

    @Override
    public ResponseEntity<List<Map<String, Object>>> allMyFind(Find find) {
        return ResponseFactory.success(findDao.select(find));
    }

    @Override
    public ResponseEntity<String> delete(Find find) {
        int count = findDao.delete(find);
        if(count>0){
            return ResponseFactory.success("Success to delete");
        }
        return ResponseFactory.badRequest("Fail to delete");
    }
}
