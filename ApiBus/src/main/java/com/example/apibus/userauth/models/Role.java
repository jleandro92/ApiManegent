package com.example.apibus.userauth.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Role {

    ADMIN("admin"),
    USER("user");

    private String role;

}
