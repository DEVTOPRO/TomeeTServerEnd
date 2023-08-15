package com.wingstop.tomeet.repository;

import com.wingstop.tomeet.model.LoginDetails;
import com.wingstop.tomeet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface LoginDetailsDao extends JpaRepository<LoginDetails,Long> {

    Optional<LoginDetails> findByUser(User user);
}

