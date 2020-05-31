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
public class Feed {
    private Integer feed_id;
    private Integer user_id;
    private Integer pet_id;
    private Date from_date;
    private Date to_date;
    private String remark;
    private Integer passed;
    private Date pass_date;
    private Integer contracted;
    private String contract;
    private String status;
}
