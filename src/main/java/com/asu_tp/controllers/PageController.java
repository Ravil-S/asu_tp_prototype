package com.asu_tp.controllers;

import com.asu_tp.models.Post;
import com.asu_tp.models.User;
import com.asu_tp.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class PageController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/monitor")
    public String monitor( Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "monitor";
    }

    @GetMapping("/tp")
    public String texp( Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "tp";
    }

    @GetMapping("/wits")
    public String blogAdd( Model model) {
        return "wits";
    }

    @PostMapping("/wits")
    public String blogPostAdd(
            @AuthenticationPrincipal User user,
            @RequestParam String title, Model model) {

        return "redirect:/wits";
    }

}
