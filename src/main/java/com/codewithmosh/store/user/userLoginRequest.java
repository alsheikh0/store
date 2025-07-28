package com.codewithmosh.store.user;


import lombok.Data;

@Data
public class userLoginRequest {
    private String email;
    private String password;
}
