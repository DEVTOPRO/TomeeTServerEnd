package com.wingstop.tomeet.repository;

import com.wingstop.tomeet.dto.UserDetailsDto;
import com.wingstop.tomeet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserDetailsDao extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);


    @Query("Select new com.wingstop.tomeet.dto.UserDetailsDto(u.userId,u.userName,u.mobileNumber,u.firstName,u.isAdmin,u.lastName,u.dataOfBirth,u.gender,u.userVerificationStatus,l.loggedInTime,u.status) " +
            "from  LoginDetails l " +
            "left JOIN  l.user u "
    )
    List<UserDetailsDto> findAllData();
}
