package com.codewithmosh.store.user;

import lombok.Data;

@Data
public class registerUserRequest {
    private String name;
    private String email;
    private String password;
}
