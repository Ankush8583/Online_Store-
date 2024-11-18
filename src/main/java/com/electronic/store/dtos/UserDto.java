package com.electronic.store.dtos;

import jakarta.persistence.Column;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

    private String imageName;
    private String userId;


    @Size(min = 3, max = 15, message = "Invalid Name !!")
    private String name;

   // @Email(message = "Invalid User Email !!")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "User email is invalid !!")
    @NotBlank(message = "Email required !!")
    private String email;

    @NotBlank(message = "Password is required !!")
    private String password;

    @Size(min = 4, max = 6, message = "Invalid gender !!")
    private String gender;
    private String about;

    //@Pattern

    //Custom Validator


   


}
