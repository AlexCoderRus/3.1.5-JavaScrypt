package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.Authentication;

public interface AuthantivationFacadeI {
    Authentication getAuthentication();
}
