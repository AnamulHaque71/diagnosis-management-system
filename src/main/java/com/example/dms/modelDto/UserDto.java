package com.example.dms.modelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String fullName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String gender;
    private String dob;
    private String bloodGroup;
    private String image;
}