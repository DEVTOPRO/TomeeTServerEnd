package com.wingstop.tomeet.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@ToString
@Table(name ="login_details")
public class LoginDetails  extends CommonModel{

    @Column(name ="login_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long loginId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="password")
    private String password;

    @Column(name="logged_in_time")
    private Date loggedInTime;
}
