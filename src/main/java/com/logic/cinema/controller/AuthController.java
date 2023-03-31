package com.logic.cinema.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("logout")
    public String getLogoutPage(){
        return "redirect:auth/login";
    }

    @GetMapping("failure")
    @PreAuthorize("hasAuthority('all:read')")
    public String getFailurePage(){
        return "failure";
    }

    @GetMapping("success")
    public String getSuccessPage() {
        return "success";
    }

}
