package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.model.User;

import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @GetMapping("/users")
    public String printUser(Model model, Principal principal) {
        model.addAttribute("messages", userService.getUser());

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);

        model.addAttribute("allRoles", roleService.findAllRoles());

        return "admin";
    }


    @GetMapping("/user")
    public String pageForUser(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }


    @GetMapping("/new")
    public String newUser(Principal principal, Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.findAllRoles());
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "addUser";
    }

    @PostMapping("/save")
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/admin/users";
    }



    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("user") User user,
                         @PathVariable("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/admin/users";
    }


    @GetMapping("/edit/{username}")
    public String edit(Model model, @PathVariable("username") String username) {
        model.addAttribute("user", userService.findByUsername(username));
        model.addAttribute("roles", roleService.findAllRoles());
        return "editUser";
    }

    @PatchMapping ("/{username}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("username") String username) {
        userService.updateUser(username, user);
        return "redirect:/admin/users";
    }

}