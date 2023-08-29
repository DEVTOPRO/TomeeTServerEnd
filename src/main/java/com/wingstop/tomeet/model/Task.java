package com.wingstop.tomeet.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ToString
@Table(name ="task")
public class Task extends CommonModel {

    @Column(name ="task_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long taskId;

    @Column(name ="task_description", nullable = false)
    private String taskDescription;

    @Column(name ="task_title")
    private String taskTitle;

    @OneToMany(mappedBy="task")
    @JsonIgnore
    private List<UserTask> userTasks;
}
