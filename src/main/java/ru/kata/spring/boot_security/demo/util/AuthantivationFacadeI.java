package ru.kata.spring.boot_security.demo.util;


import org.springframework.security.core.Authentication;

public interface AuthantivationFacadeI {
    Authentication getAuthentication();
}
