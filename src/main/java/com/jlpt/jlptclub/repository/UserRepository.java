package com.jlpt.jlptclub.repository;

import com.jlpt.jlptclub.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmailAndPassword(String email,String password);
    public User findUserByEmail(String email);
}
