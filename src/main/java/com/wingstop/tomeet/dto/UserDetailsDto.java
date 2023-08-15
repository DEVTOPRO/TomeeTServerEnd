package com.wingstop.tomeet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import com.wingstop.tomeet.constants.EnumContainer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailsDto {
    private Long userId;
    private String userName;
    private String password;
    private String mobileNumber;
    private String firstName;
    private Boolean isAdmin;
    private String lastName;
    private Date dataOfBirth;
    private String gender;
    private String token;
    private Integer status;
    private String userVerificationStatus ;
    private Date loggedInTime;

    public UserDetailsDto(Long userId, String userName,  String mobileNumber, String firstName, Boolean isAdmin, String lastName, Date dataOfBirth, EnumContainer.Gender gender,EnumContainer.UserVerificationStatus userVerificationStatus, Date loggedInTime,Integer status) {
        this.userId = userId;
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.firstName = firstName;
        this.isAdmin = isAdmin;
        this.lastName = lastName;
        this.dataOfBirth = dataOfBirth;
        this.gender = String.valueOf(gender);
        this.userVerificationStatus = String.valueOf(userVerificationStatus);
        this.loggedInTime = loggedInTime;
        this.status = status;
    }
}
