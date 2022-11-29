package com.i2i.ibus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.ibus.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByMailId(String mailId);

    Optional<User> findByPhoneNumber(String phoneNumber);
}
