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
public class Message {
    private Integer message_id;
    private String title;
    private Integer sender_id;
    private Integer receiver_id;
    private Date date;
    private Boolean readed;
    private String content;

}
