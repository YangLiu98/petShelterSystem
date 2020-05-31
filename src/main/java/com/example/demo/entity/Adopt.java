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
public class Adopt {
    private Integer adopt_id;
    private Integer user_id;
    private Integer pet_id;
    private Date date;
    private String contract;
    private String remark;
}
