package com.wingstop.tomeet.dto;

import com.sun.istack.NotNull;
import com.wingstop.tomeet.constants.EnumContainer;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
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
    private String userVerificationStatus ;
}
