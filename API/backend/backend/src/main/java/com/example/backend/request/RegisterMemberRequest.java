package com.example.backend.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMemberRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
