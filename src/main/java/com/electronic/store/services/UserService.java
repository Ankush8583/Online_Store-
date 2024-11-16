package com.electronic.store.services;

import com.electronic.store.dtos.UserDto;
import com.electronic.store.entities.User;

import java.util.List;

public interface UserService {

    //create

     UserDto craeteUser(UserDto userDto);


    //update

      UserDto updateUser(UserDto userDto, String userId);


    //delete

     void deleteUser(String userId);

    //get all user

     List<UserDto> getAllUser();

    //get single user by email

     UserDto getUserByEmail(String email);

    //search user

    List<UserDto> searchUser(String keyword);






}
