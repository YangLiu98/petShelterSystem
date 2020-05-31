package com.example.demo.service;

import com.example.demo.entity.Find;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
public interface FindService {
    public ResponseEntity<String> insert(Find find);

    public ResponseEntity<Find> getDetail(Find find);

    public ResponseEntity<List<Map<String, Object>>> allMyFind(Find find);

    public ResponseEntity<String> delete(Find find);

}
