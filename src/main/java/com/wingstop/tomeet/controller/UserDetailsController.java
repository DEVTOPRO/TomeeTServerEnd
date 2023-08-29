package com.wingstop.tomeet.controller;

import com.wingstop.tomeet.dto.ResponseObject;
import com.wingstop.tomeet.dto.UserData;
import com.wingstop.tomeet.dto.UserDetailsDto;
import com.wingstop.tomeet.service.UserDetailsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

           System.out.println(e.fillInStackTrace());
           return new ResponseEntity<>(new ResponseObject(500, "Fail", e.getLocalizedMessage(), null), HttpStatus.BAD_REQUEST);

    }
    }

    @PostMapping(value = "/forgot_pin")
    @ApiOperation(value = "Forgot Pin", notes = "Forgot pin")
    public ResponseEntity<?> forgotPin(@RequestParam("username") String userName) {
        try {
            if (null != userName) {
                return new ResponseEntity<>(userDetailsService.forgotPin(userName), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseObject(500, "Fail", "request object is null", null),
                        HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return new ResponseEntity<>(new ResponseObject(500, "Fail", e.getLocalizedMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/verify_token")
    @ApiOperation(value = "Verify Token", notes = "Verify Token")
    public ResponseEntity<?> verifyToken(@RequestParam("verifyToken") String verifyToken,@RequestParam("userId") Long userId){
        try{
            if(null != verifyToken){
                return  new ResponseEntity<>(userDetailsService.verifyToken(verifyToken,userId),HttpStatus.OK);

            }
            else{
                return new ResponseEntity<>(new ResponseObject(500, "Fail", "request token is null", null), HttpStatus.BAD_REQUEST);

            }

        }
        catch(Exception e){

            return new ResponseEntity<>(new ResponseObject(500, "Fail", e.getMessage(), null), HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping(value = "/set_mpin")
    @ApiOperation(value = "Set Mpin", notes = "Set Mpin")
    public ResponseEntity<?> setMpin(@RequestBody UserData userData){
        try{
            if(null != userData){
                return  new ResponseEntity<>(userDetailsService.setMpin(userData.getUserName(),userData.getPassword()),HttpStatus.OK);

            }
            else{
                return new ResponseEntity<>(new ResponseObject(500, "Fail", "request object is null", null), HttpStatus.BAD_REQUEST);

            }

        }
        catch(Exception e){

            return new ResponseEntity<>(new ResponseObject(500, "Fail", e.getMessage(), null), HttpStatus.BAD_REQUEST);

        }
    }
    @PostMapping(value = "/login")
    @ApiOperation(value = "login", notes = "login")
    public ResponseEntity<?> login(@RequestBody UserData userDetailsDto){
        try{
            if(null != userDetailsDto){
                return  new ResponseEntity<>(userDetailsService.login(userDetailsDto.getUserName(),userDetailsDto.getPassword()),HttpStatus.OK);

            }
            else{
                return new ResponseEntity<>(new ResponseObject(500, "Fail", "request object is null", null), HttpStatus.BAD_REQUEST);

            }

        }
        catch(Exception e){

            return new ResponseEntity<>(new ResponseObject(500, "Fail", e.getMessage(), null), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping(value = "/list_of_users")
    @ApiOperation(value = "list of users", notes = "list of users")
    public ResponseEntity<?> listOfUSers(){
        try{

            List<UserDetailsDto> userData = userDetailsService.getListOfUsers();
            if(userData.size()>0)
                return  new ResponseEntity<>(userData,HttpStatus.OK);
            else
                return new ResponseEntity<>(new ResponseObject(500, "Fail", "request object is null", null), HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){

            return new ResponseEntity<>(new ResponseObject(500, "Fail", e.getMessage(), null), HttpStatus.BAD_REQUEST);

        }
    }





}


