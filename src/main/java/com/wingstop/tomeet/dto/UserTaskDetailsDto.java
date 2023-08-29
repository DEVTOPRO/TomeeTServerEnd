package com.wingstop.tomeet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserTaskDetailsDto {

    private String taskDescription;

    private String taskTitle;

    private  Long taskId;

    private Long userId;

    private Date startDate;

    private Date endDate;


    public UserTaskDetailsDto(String taskDescription, String taskTitle, Long taskId, Long userId, Date startDate, Date endDate) {
        this.taskDescription = taskDescription;
        this.taskTitle = taskTitle;
        this.taskId = taskId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
