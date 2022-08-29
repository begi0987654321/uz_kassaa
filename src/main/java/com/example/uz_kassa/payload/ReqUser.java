package com.example.uz_kassa.payload;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
public class ReqUser {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private String email;
    @NotBlank //probel ham bolishi keremas
    private String firstName;
    @NotBlank
    private String lastName;

    @NotBlank
    private String roles;
}
