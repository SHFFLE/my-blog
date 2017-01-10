package com.codeup.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class AuthenticationController {

    @Autowired
    Users userDao;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

//    @GetMapping("/logout")
//    public String logoutUser(HttpServletRequest request, HttpServletResponse response){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/posts";
//    }

    @PostMapping("/register")
    public String createUser(@Valid User user, Errors validation, Model model) {
        if (validation.hasErrors()) {
            model.addAttribute(validation.getAllErrors());
            model.addAttribute("user", user);
            return "/register";
        }
        System.out.println("username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        userDao.save(user);
        return "redirect:/posts";
    }
}