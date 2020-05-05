package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Integer user_id;
    private String user_name;
    private String first_name;
    private String last_name;
    private String password;
    private String phone;
    private String email;
    private String address;
    private Boolean is_admin;
    private String icon;
}
