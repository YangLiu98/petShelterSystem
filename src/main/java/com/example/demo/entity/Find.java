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
public class Find {
    private Integer find_id;
    private Integer user_id;
    private Integer pet_id;
    private Date from_date;
    private Date to_date;
    private Integer reward;
    private Integer reward_value;
    private String reward_others;
    private String remark;
    private Integer found;
}
