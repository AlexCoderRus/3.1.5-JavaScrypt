package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserDtoServiceImp implements UserDtoService{

    private final UserDetailServiceInterface userDetailServiceInterface;

    public UserDtoServiceImp(UserDetailServiceImp userDetailServiceImp) {
        this.userDetailServiceInterface = userDetailServiceImp;
    }

    @Override
    public List<UserDto> allUserDto() {
        return userDetailServiceInterface.allUser().stream().map(userDetailServiceInterface :: convertToDTO).toList();
    }

    @Override
    public boolean saveUserDto(UserDto userDto) {
        return userDetailServiceInterface.saveUser(userDetailServiceInterface.convertToUser(userDto));
    }

    @Override
    public void updateDto(UserDto userDto) {
        userDetailServiceInterface.update(userDetailServiceInterface.convertToUser(userDto));
    }

    @Override
    public UserDto findUserById(Long id){
        return userDetailServiceInterface.convertToDTO(userDetailServiceInterface.findUserById(id));
    }
}
