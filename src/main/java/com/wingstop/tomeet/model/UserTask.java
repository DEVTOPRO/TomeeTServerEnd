package com.wingstop.tomeet.model;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@ToString
@Table(name ="user_task_assign")
public class UserTask extends CommonModel {

    @Column(name ="usertask_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long userTaskId;


    @Column(name="user_id")
    private Long userId;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id",referencedColumnName = "task_id")
    private Task task;

    @Column(name ="start_date")
    private Date startDate;

    @Column(name ="end_date")
    private Date endDate;

}
