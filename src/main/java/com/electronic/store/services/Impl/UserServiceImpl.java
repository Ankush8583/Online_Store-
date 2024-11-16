package com.electronic.store.services.Impl;

import com.electronic.store.dtos.UserDto;
import com.electronic.store.entities.User;
import com.electronic.store.repositories.UserRepository;
import com.electronic.store.services.UserService;
import lombok.Builder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
//@Service
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto craeteUser(UserDto userDto) {

        String userId = UUID.randomUUID().toString();
         userDto.setUserId(userId);
        User user=dtoToEntity(userDto);
       User savedUser= userRepository.save(user);

       UserDto newDto=entityToDto(savedUser);

       return newDto;
    }



    @Override
    public UserDto updateUser(UserDto userDto, String userId) {

         User user= userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given id"));
         user.setName(userDto.getName());
         user.setAbout(userDto.getAbout());
         user.setImageName(userDto.getImageName());
         user.setGender(userDto.getGender());


        User updatedUser = userRepository.save(user);
        UserDto updateDto = entityToDto(updatedUser);



        return updateDto;
    }

    @Override
    public void deleteUser(String userId) {

       User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given id"));

        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> users =  userRepository.findAll();
         List<UserDto> dtoList=users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user= userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not present with given email !!"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        return List.of();
    }


    private UserDto entityToDto(User savedUser) {
//       UserDto userDto=UserDto.builder().userId(savedUser.getUserId())
//               .name(savedUser.getName())
//               .email(savedUser.getEmail())
//               .gender(savedUser.getGender())
//               .about(savedUser.getAbout())
//               .password(savedUser.getPassword())
//               .imageName(savedUser.getImageName())
//               .build();

            return mapper.map(savedUser,UserDto.class);
    }

    private User dtoToEntity(UserDto userDto) {
//
//          User user = User.builder()
//                  .userId(userDto.getUserId()).
//                  name(userDto.getName()).
//                  email(userDto.getEmail()).
//                  about(userDto.getAbout()).
//                  gender(userDto.getGender())
//                  .imageName(userDto.getImageName())
//                  .password(userDto.getPassword())
//                  .build();
          return mapper.map(userDto,User.class);
    }



}
