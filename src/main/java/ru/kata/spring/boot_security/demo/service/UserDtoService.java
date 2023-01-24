package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dto.UserDto;
import java.util.List;

public interface UserDtoService {
    List<UserDto> allUserDto();
    boolean saveUserDto(UserDto userDto);
    void updateDto(UserDto userDto);

    UserDto findUserById(Long id);

}
