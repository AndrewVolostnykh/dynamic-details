package com.lessons.spring.springdb.service;

import com.lessons.spring.springdb.model.RegularUser;
import com.lessons.spring.springdb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<RegularUser> list() {
        return userRepository.findAll();
    }

    public RegularUser create(RegularUser regularUser) {

        return userRepository.save(regularUser);
    }
}
