package com.wingstop.tomeet.model;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_job_details")
@ToString
@Data
public class UserJobDetails extends CommonModel{

    @Id
    @Column(name = "user_job_details_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userJobDetailsId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="job_role_id")
    private Long jobRoleId;



}
