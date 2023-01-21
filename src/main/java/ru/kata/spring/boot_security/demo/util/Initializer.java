package ru.kata.spring.boot_security.demo.util;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceInterface;
import ru.kata.spring.boot_security.demo.service.UserDetailServiceImp;
import ru.kata.spring.boot_security.demo.service.UserDetailServiceInterface;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Initializer {
    private final UserDetailServiceInterface userService;
    private final RoleServiceInterface roleService;

    public Initializer(UserDetailServiceImp userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void createRolesAndBasicUsers() {
        if (roleService.findAllRoles().isEmpty() || userService.allUser().isEmpty()) {
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");
            List<Role> adminList = new ArrayList<>();
            List<Role> userList = new ArrayList<>();

            roleService.addRole(roleAdmin);
            roleService.addRole(roleUser);

            adminList.add(roleAdmin);
            userList.add(roleUser);


            User admin = new User("admin@mail.ru", "100", "admin", 22, "admin");
            admin.addRole(adminList);
            User user = new User("user@mail.ru", "100", "user", 33, "user");
            user.addRole(userList);

            userService.saveUser(user);
            userService.saveUser(admin);
        }
    }
}