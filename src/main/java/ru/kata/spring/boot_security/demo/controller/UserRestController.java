package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserDetailServiceImp;
import ru.kata.spring.boot_security.demo.service.UserDetailServiceInterface;
import ru.kata.spring.boot_security.demo.service.AuthantivationFacadeI;
import ru.kata.spring.boot_security.demo.service.AuthenticationFacade;

@RestController
public class UserRestController {
    private final AuthantivationFacadeI authantivationFacadeI;
    private final UserDetailServiceInterface userDetailServiceInterface;

    public UserRestController(AuthenticationFacade authenticationFacade, UserDetailServiceImp userDetailService) {
        this.authantivationFacadeI = authenticationFacade;
        this.userDetailServiceInterface = userDetailService;
    }


    @GetMapping("/api/user")
    public ResponseEntity<UserDto> showUser() {
        authantivationFacadeI.getAuthentication();
        User user = (User) authantivationFacadeI.getAuthentication().getPrincipal();
        return new ResponseEntity<>(userDetailServiceInterface.convertToDTO(user), HttpStatus.OK);
    }
}
