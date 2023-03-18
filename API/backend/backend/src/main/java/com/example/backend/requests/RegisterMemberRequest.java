package com.example.backend.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMemberRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String phoneNumber;


}
