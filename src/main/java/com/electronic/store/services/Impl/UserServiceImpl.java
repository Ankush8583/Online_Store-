package com.electronic.store.services.Impl;

import com.electronic.store.dtos.PageableResponse;
import com.electronic.store.dtos.UserDto;
import com.electronic.store.entities.User;
import com.electronic.store.exceptions.ResourceNotFoundException;
import com.electronic.store.helper.Helper;
import com.electronic.store.repositories.UserRepository;
import com.electronic.store.services.UserService;
import lombok.Builder;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder

//@Service
@Component

@Service

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

         User user= userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));
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

       User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));

        userRepository.delete(user);
    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize) {


         Pageable pageable = PageRequest.of(pageNumber, pageSize);
        org.springframework.data.domain.Page<User> page = userRepository.findAll(pageable);
                               
      //   List<UserDto> dtoList=users.getContent().stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        
        //   PageableResponse<UserDto> response = new PageableResponse<>();
        //   response.setContent(dtoList);
        //   response.setPageNumber(users.getNumber());
        //   response.setPageSize(users.getSize());
        //   response.setTotalElement(users.getTotalElements());
        //   response.setTotalPages(users.getTotalPages());
        //   response.setLastPage(users.isLast());

       PageableResponse<UserDto> response= Helper.getPageableResponse(page, UserDto.class);

        return response;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user= userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not present with given email !!"));
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
