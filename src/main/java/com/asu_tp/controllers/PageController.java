package com.asu_tp.controllers;

import com.asu_tp.models.Directional;


import com.asu_tp.repo.DirectionalRepository;

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
    private DirectionalRepository directionalRepository;

    @GetMapping("/monitor")
    public String monitor( Model model) {

        return "monitor";
    }

    @GetMapping("/tp")
    public String texp( Model model) {

        return "tp";
    }

    @GetMapping("/wits")
    public String wits( Model model) {
        Iterable<Directional> datas = directionalRepository.findAll();
        model.addAttribute("data", datas);
        return "wits";
    }

}
