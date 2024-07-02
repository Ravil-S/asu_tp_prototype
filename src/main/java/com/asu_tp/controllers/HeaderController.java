package com.asu_tp.controllers;

import com.asu_tp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@ControllerAdvice
public class HeaderController {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addModelInformation(Model model){

        String user = userService.isUserLogged().get("username");
      //  // System.out.println("username "+user);
        if (user.equals("null")) { user="";}

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        model.addAttribute("logged_username", "Дата: "+dateFormat.format(cal.getTime())+
                " Время: "+timeFormat.format(cal.getTime())+ " Пользователь: "+user);
    }
}