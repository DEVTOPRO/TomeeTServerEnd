package com.wingstop.tomeet.controller;

import com.wingstop.tomeet.dto.ResponseObject;
import com.wingstop.tomeet.dto.UserDetailsDto;
import com.wingstop.tomeet.service.UserDetailsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsController {

    private final UserDetailsService userDetailsService;
    @PostMapping(value = "/register")
    @ApiOperation(value = "Register User", notes = "Register A New User")
    public ResponseEntity<?> signUp(@RequestBody UserDetailsDto userDetailsDto){
       try{
           if(null != userDetailsDto){
               return  new ResponseEntity<>(userDetailsService.saveUser(userDetailsDto),HttpStatus.OK);

           }
           else{
               return new ResponseEntity<>(new ResponseObject(500, "Fail", "request object is null", null), HttpStatus.BAD_REQUEST);

           }

       }
    catch(Exception e){

           return new ResponseEntity<>(new ResponseObject(500, "Fail", e.getMessage(), null), HttpStatus.BAD_REQUEST);

    }
    }

}


