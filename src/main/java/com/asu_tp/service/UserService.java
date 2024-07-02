package com.asu_tp.service;

import com.asu_tp.models.Role;
import com.asu_tp.models.User;
import com.asu_tp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public Map<String,String> isUserLogged(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username="anon";
        String status="anon";

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        if (username.equals("anonymousUser")) {
            status="anon";
            username="anon";
        } else {
            //Set<Role> userRoles= userRepository.findByUsername(username).getRoles();
            //for (Role r:userRoles) {

            User user = (User)principal;
            for (Role r : user.getRoles()) {
                //  status = r.toString();
                if ( r.equals(Role.USER) && !status.equals("admin") ){
                    status="user";
                }
                if ( r.equals(Role.ADMIN)){
                    status="admin";
                }
            }
        }

        Map<String,String> usermap = new HashMap<>();
        usermap.put("status", status);
        usermap.put("username", username);

        return usermap;
    }
}
