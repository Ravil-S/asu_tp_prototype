package com.asu_tp.controllers;

import com.asu_tp.models.Role;
import com.asu_tp.models.User;
import com.asu_tp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user/userList";
    }

    @GetMapping("{user}")
    public String userEdit(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user/userEdit";
    }

    @PostMapping("{user}")
    public String userSave(@RequestParam String username, @RequestParam(name = "roles[]", required = false) String[] roles, @PathVariable User user) {
        user.setUsername(username);
        user.getRoles().clear();

        if (roles != null) {
            Arrays.stream(roles).forEach(r -> user.getRoles().add(Role.valueOf(r)));
        }
        userRepository.save(user);
        return "redirect:/user";

    }
}
