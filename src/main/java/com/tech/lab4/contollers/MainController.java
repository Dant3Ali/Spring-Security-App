package com.tech.lab4.contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/admin")
    public String admin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Current user: " + authentication.getName());
        System.out.println("User roles: " + authentication.getAuthorities());
        return "admin";
    }

    @GetMapping("/authorized")
    public String auth(){
        return "authorized";
    }

    @GetMapping("/any")
    public String any(){
        return "any";
    }
}
