package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserDetailServiceImp;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserDetailServiceImp userDetailServiceImp;

    private final RoleService roleService;

    public AdminRestController(UserDetailServiceImp userDetailServiceImp, RoleService roleService) {
        this.userDetailServiceImp = userDetailServiceImp;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> allUsers () {
        return new ResponseEntity<>(userDetailServiceImp.allUser().stream().map(userDetailServiceImp :: convertToDTO).toList(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> findUser(@PathVariable Long id) {
        return new ResponseEntity<>(userDetailServiceImp.convertToDTO(userDetailServiceImp.findUserById(id)), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> create (@ModelAttribute UserDto userDto) {
        userDetailServiceImp.saveUser(userDetailServiceImp.convertToUser(userDto));
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> update (@ModelAttribute UserDto userDto) {
        userDetailServiceImp.update(userDetailServiceImp.convertToUser(userDto));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userDetailServiceImp.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.findAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    ResponseEntity<Role> getRoleById(@PathVariable("id") Long id){
        return new ResponseEntity<>(roleService.findRoleById(id), HttpStatus.OK);
    }


}
