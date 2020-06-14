package com.example.demo.controller;

import com.example.demo.entity.Pet;
import com.example.demo.entity.TagSet;
import com.example.demo.service.PetService;
import com.example.demo.util.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pet")
public class PetController {


    @Autowired
    private PetService petService;

    @PostMapping("insert")
    public ResponseEntity<String> signUp(@RequestBody Pet pet){
        System.out.println(pet);
        return petService.insertPet(pet);
    }

    //根据pet_id返回pet信息，未找到返回null
    @PostMapping("detail")
    public ResponseEntity<Pet> getDetial(@RequestBody Pet pet){
        System.out.println(pet);
        return petService.getDetial(pet);
    }

    //返回所有宠物的信息
    @PostMapping("all")
    public ResponseEntity<List<Map<String,Object>>> getAll(){
        return petService.getAll();
    }

    //精确查找
    @PostMapping("select")
    public ResponseEntity<List<Map<String,Object>>> select(@RequestBody Pet pet){
        return petService.select(pet);
    }

    //Tag查找，支持最多5个Tag
    //该方法返回值已根据Tag相关度排序
    @PostMapping("selectByTag")
    public ResponseEntity<List<Map<String,Object>>> selectByTag(@RequestBody TagSet tagSet){
        List<Map<String,Object>> temp= petService.selectByTag(tagSet);
        if(tagSet.getType()!=null){
            Iterator<Map<String,Object>> it = temp.iterator();
            while(it.hasNext()){
                Map<String,Object> x = it.next();
                if(!x.get("type").toString().equals(tagSet.getType())){
                    it.remove();
                }
            }
        }
        return ResponseFactory.success(temp);
    }

    //更新宠物信息
    @PostMapping("update")
    public ResponseEntity<String> update(@RequestBody Pet pet){
        System.out.println(pet);
        return petService.update(pet);
    }
}
