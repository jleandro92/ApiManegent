package com.example.apibus.userauth.dto;

import com.example.apibus.userauth.models.Role;

public record RegistreDto(String login, String password, Role role) {

}
