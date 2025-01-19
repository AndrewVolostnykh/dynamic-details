package com.lessons.spring.springdb.model;

import lombok.Data;

@Data
public class GuestUser implements User {

    private Long id;
    private String email;
}
