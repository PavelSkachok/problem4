package com.mytime.forall.controllers;

import com.mytime.forall.domain.Role;
import com.mytime.forall.domain.User;
import com.mytime.forall.repositories.RoleRepo;
import com.mytime.forall.services.RoleService;
import com.mytime.forall.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
@Autowired
    private RoleService roleService;
    @GetMapping(value = {"/registrationform"})
    public String showAddUser(Model model) {
        Role role1 = new Role("USER");
        Role role2 = new Role("ADMIN");
        roleService.saveRole(role1);
        roleService.saveRole(role2);
        User user = new User();
        model.addAttribute("user", user);

        return "registrationform";
    }

    @PostMapping(value = "/registrationform")
    public String addUser(Model model,
                          @ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/adminpage";
    }
}