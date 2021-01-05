package com.mytime.forall.controllers;

import com.mytime.forall.domain.User;
import com.mytime.forall.repositories.UserRepo;
import com.mytime.forall.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
//@RequestMapping("/authenticated")
public class AdminPageController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;

    private String[] chBox;

    @GetMapping("/adminpage")
    public String registrationPage(Model model,Principal principal) {
        List<User> userList = userRepo.findAll();
        model.addAttribute("reg", userList);
        model.addAttribute("userNameLogin", principal.getName());
        return "adminpage";
    }

    @PostMapping("/adminpage")
    public String unsuccessBlock(@RequestParam(value = "chBox", required = false) List<String> chBox,
                                 @RequestParam(value = "userStatus", required = false) String userStatus,
                                 @RequestParam(value = "userDelete", required = false) String userDelete, Principal principal) {
        boolean isCurrentUser = (chBox != null) && chBox.contains(String.valueOf(userRepo.findByUsername(principal.getName()).getId()));
        if (isCurrentUser && userStatus!=null && userStatus.equals("blocked")) {
           updateStatus(userStatus,chBox);
            return "redirect:/login";
        } else if (isCurrentUser && userDelete != null) {
            deleteUsers(userDelete,chBox);
            return "redirect:/login";
        }
        updateStatus(userStatus,chBox);
        deleteUsers(userDelete,chBox);
        return "redirect:/adminpage";
    }

    private void updateStatus(String userStatus, List<String> chBox) {
        if (chBox != null && userStatus!=null) {
            for (String i : chBox) {
                User user = userRepo.getOne(Integer.parseInt(i));
                user.setEnabled(!userStatus.equals("blocked"));
                user.setStatus(userStatus);
                userRepo.save(user);
            }
        }

    }

    private void deleteUsers(String userDelete, List<String> chBox) {
        if (userDelete != null && chBox != null) {
            for (String i : chBox) {
                userRepo.deleteById(Integer.parseInt(i));
            }
        }
    }
}
