package com.codewithmosh.store.dtos;


import lombok.Data;

@Data
public class userLoginRequest {
    private String email;
    private String password;
}
