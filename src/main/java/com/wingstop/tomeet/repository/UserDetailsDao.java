package com.wingstop.tomeet.repository;

import com.wingstop.tomeet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@Repository
public interface UserDetailsDao extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);


}
