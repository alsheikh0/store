package com.codewithmosh.store.user;

import lombok.Data;

@Data
public class changePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
