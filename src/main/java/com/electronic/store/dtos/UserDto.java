package com.electronic.store.dtos;

import jakarta.persistence.Column;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

    private String imageName;
    private String userId;
    private String name;
    private String email;
    private String password;
    private String gender;
    private String about;

}
