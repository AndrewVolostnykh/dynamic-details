package com.lessons.spring.springdb.repository;

import com.lessons.spring.springdb.model.RegularUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<RegularUser, Integer> {
}
