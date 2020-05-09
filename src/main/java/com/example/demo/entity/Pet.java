package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pet {
    private Integer pet_id;
    private String type;
    private  String species;
    private String breed;
    private String color;
    private String size;
    private String sex;
    private Date birthday;
    private String remark;
    private String tag;
    private String main_pic;
    private String pic;
    private String video;
}
