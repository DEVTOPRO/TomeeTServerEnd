package com.wingstop.tomeet.controller;


import com.wingstop.tomeet.dto.ResponseObject;
import com.wingstop.tomeet.dto.UserDetailsDto;
import com.wingstop.tomeet.model.UserTask;
import com.wingstop.tomeet.service.TaskDetailsService;
import com.wingstop.tomeet.service.UserDetailsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/task")
public class TaskDetailsController {

    private final TaskDetailsService taskDetailsService;

    @GetMapping(value = "/getUserTasks")
    @ApiOperation(value = "get user tasks", notes = "get user tasks")
    public ResponseEntity<?> getUserTasks(){
        try{
            return taskDetailsService.getListOfUsersTasks();
        }
        catch(Exception e){

            return new ResponseEntity<>(new ResponseObject(500, "Fail", e.getMessage(), null), HttpStatus.BAD_REQUEST);

        }
    }
}
