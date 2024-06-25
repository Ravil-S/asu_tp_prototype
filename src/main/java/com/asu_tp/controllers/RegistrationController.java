package com.asu_tp.controllers;

import com.asu_tp.models.Role;
import com.asu_tp.models.User;
import com.asu_tp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/registration")
    public String registration( Model model) {
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB !=null){
            model.addAttribute("message", "User exists!");
            return "user/registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }


}
