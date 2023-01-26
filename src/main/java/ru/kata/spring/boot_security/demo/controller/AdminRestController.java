package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserDtoService userDtoService;

    private final UserDetailServiceInterface userDetailServiceImp;

    private final RoleServiceInterface roleService;

    public AdminRestController(UserDtoService userDtoService, UserDetailServiceInterface userDetailServiceInterface, RoleService roleService) {
        this.userDtoService = userDtoService;
        this.userDetailServiceImp = userDetailServiceInterface;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> allUsers () {
        return new ResponseEntity<>(userDtoService.allUserDto(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> findUser(@PathVariable Long id) {
        return new ResponseEntity<>(userDtoService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<UserDto> create (@RequestBody UserDto userDto) {
        userDtoService.saveUserDto(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PatchMapping("/user/edit/{id}")
    public ResponseEntity<UserDto> update (@RequestBody UserDto user, @PathVariable("id") Long id) {
       userDtoService.updateDto(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userDetailServiceImp.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.findAllRoles(), HttpStatus.OK);
    }
}
