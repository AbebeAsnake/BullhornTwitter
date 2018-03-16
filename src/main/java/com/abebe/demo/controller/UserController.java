package com.abebe.demo.controller;

import com.abebe.demo.model.AppUser;
import com.abebe.demo.repo.AppRoleRepository;
import com.abebe.demo.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    AppUserRepository userRepository;

    @Autowired
    AppRoleRepository roleRepository;
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("users", new AppUser());
        return "index";

    }
    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("users", new AppUser());
        return "registeruser";

    }
    @GetMapping("/register")
    public String showForm(Model model){
        model.addAttribute("users", new AppUser());
        return "registeruser";
    }
    @RequestMapping("/register")
    public String addItems(Model model , AppUser user){

        user.addRole(roleRepository.findAppRoleByRoleName("USER"));
        userRepository.save(user);
        return "redirect:/";

    }
}
