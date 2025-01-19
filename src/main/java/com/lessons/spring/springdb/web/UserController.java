package com.lessons.spring.springdb.web;

import com.lessons.spring.springdb.model.RegularUser;
import com.lessons.spring.springdb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<RegularUser> list() {
        return userService.list();
    }

    @PostMapping
    public RegularUser create(@RequestBody RegularUser regularUser) {
        return userService.create(regularUser);
    }
}
