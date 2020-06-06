package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TagSet {
    private String tag1;
    private String tag2;
    private String tag3;
    private String tag4;
    private String tag5;

    private String type;
}
