package com.aleklew.ballot.modules.profiles.controllers;

import com.aleklew.ballot.modules.profiles.dbmodels.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("hasRole('admin') or hasRole('user')")
public class UserProfileController {

    @RequestMapping(value = "/getUserId", method = RequestMethod.GET)
    public int getUserId() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getID();
    }
}
