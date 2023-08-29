package com.wingstop.tomeet.serviceImpl;

import com.wingstop.tomeet.dto.ResponseObject;
import com.wingstop.tomeet.dto.UserTaskDetailsDto;
import com.wingstop.tomeet.model.UserTask;
import com.wingstop.tomeet.repository.UserTaskDetailsDao;
import com.wingstop.tomeet.service.TaskDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskDetailsServiceImpl implements TaskDetailsService {

    private final UserTaskDetailsDao userTaskDetailsDao;


    @Override
    public ResponseEntity<?> getListOfUsersTasks() {
        List<UserTaskDetailsDto> userTaskList = userTaskDetailsDao.findAllData();
        if (!userTaskList.isEmpty()) {
            return new ResponseEntity<>(new ResponseObject(200, "Success", "Success", userTaskList), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseObject(200, "Success", "Task Data not Present", userTaskList), HttpStatus.OK);
        }
    }
}
