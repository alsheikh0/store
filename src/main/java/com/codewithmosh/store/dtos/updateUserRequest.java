package com.codewithmosh.store.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class updateUserRequest {
    @JsonProperty()
    private String name;
    @JsonProperty()
    private String email;
}
