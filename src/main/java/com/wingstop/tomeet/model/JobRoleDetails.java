package com.wingstop.tomeet.model;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="job_role_details")
@ToString
@Data
public class JobRoleDetails extends CommonModel{

    @Id
    @Column(name="job_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobRoleId;

    @Column(name="job_role_name")
    private String jobRoleName;

    @Column(name="job_role_description")
    private String jobRoleDescription;

}
