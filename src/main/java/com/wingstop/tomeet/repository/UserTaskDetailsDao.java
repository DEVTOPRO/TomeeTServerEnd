package com.wingstop.tomeet.repository;


import com.wingstop.tomeet.dto.UserDetailsDto;
import com.wingstop.tomeet.dto.UserTaskDetailsDto;
import com.wingstop.tomeet.model.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskDetailsDao extends JpaRepository<UserTask, Long>  {

    @Query("Select new com.wingstop.tomeet.dto.UserTaskDetailsDto(t.taskDescription,t.taskTitle,t.taskId,u.userId,u.startDate,u.endDate) " +
            "from  UserTask u " +
            "left JOIN  u.task t "
    )
    List<UserTaskDetailsDto> findAllData();
}
