package com.electronic.store.controller;


import com.electronic.store.dtos.ApiResponseMessage;
import com.electronic.store.dtos.UserDto;
import com.electronic.store.services.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
     private UserService userService;

    //create
    @PostMapping

      public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){



          UserDto userDto1 = userService.craeteUser(userDto);
           return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
      }


    //update

    @PutMapping("/{userId}")

      public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("userId") String userId, @RequestBody UserDto userDto){



        UserDto updateUserDto=userService.updateUser(userDto, userId);

        return new ResponseEntity<>(updateUserDto,HttpStatus.OK);
      }


    //delete

    @DeleteMapping("/{userId}")
         public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId){

            userService.deleteUser(userId);
           ApiResponseMessage message = ApiResponseMessage.builder().message("User is deleted successfully !!").success(true).status(HttpStatus.OK).build();
            return new ResponseEntity<>(message,HttpStatus.OK);
         }

    //get all

     @GetMapping
     public ResponseEntity<List <UserDto>> getAllUsers(){

        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
     }

    //get by email

        @GetMapping("/email/{email}")

        public ResponseEntity<UserDto> getUserByEmail(@Valid @PathVariable String email){

         return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
        }



    @GetMapping("/search/{keywords}")
    public ResponseEntity<List <UserDto>> searchUser(@PathVariable String keywords){

        return new ResponseEntity<>(userService.searchUser(keywords),HttpStatus.OK);
    }


}
