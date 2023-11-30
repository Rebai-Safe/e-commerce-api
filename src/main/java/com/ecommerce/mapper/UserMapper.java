package com.ecommerce.mapper;

import com.ecommerce.dto.UserDto;
import com.ecommerce.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDto userToUserDto(User user){
        UserDto userDto = new UserDto();
         userDto.setUserName(user.getUserName());
         userDto.setUserFirstName(user.getUserProfile().getUserFirstName());
         userDto.setUserLastName(user.getUserProfile().getUserLastName());
         userDto.setAddress(user.getUserProfile().getAddress());
         userDto.setPhoneNumber(user.getUserProfile().getPhoneNumber());
         userDto.setRoles(user.getRoles());
         return userDto;

    }
}
