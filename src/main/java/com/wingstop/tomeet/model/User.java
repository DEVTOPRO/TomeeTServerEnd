package com.wingstop.tomeet.model;


import com.sun.istack.NotNull;
import com.wingstop.tomeet.constants.EnumContainer;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_details")
@ToString
@Data
public class User extends CommonModel{

    @Column(name="user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="user_name",unique = true)
    private String userName;


    @Column(name = "mobileNumber",unique = true)
    private String mobileNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name="is_admin")
    private Boolean isAdmin;

    @Column(name="data_of_birth")
    private Date dataOfBirth;

    @Column(name="gender")
    @NotNull
    @Enumerated(EnumType.STRING)
    private EnumContainer.Gender gender;

    @Column(name = "token")
    private String token;

    @Column(name = "user_verification_status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private EnumContainer.UserVerificationStatus userVerificationStatus ;


    public User() {

    }
}
