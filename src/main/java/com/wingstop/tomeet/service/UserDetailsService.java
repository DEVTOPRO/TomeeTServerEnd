package com.wingstop.tomeet.service;

import com.wingstop.tomeet.dto.ResponseObject;
import com.wingstop.tomeet.dto.UserDetailsDto;
import org.apache.commons.mail.EmailException;

import java.util.List;

public interface UserDetailsService {
    ResponseObject saveUser(UserDetailsDto userDetailsDto) throws EmailException;

    ResponseObject forgotPin(String userName) throws EmailException;

    ResponseObject verifyToken(String verifyToken, Long userId);

    ResponseObject setMpin(String userName, String password);

    ResponseObject login(String userName, String password);

    List<UserDetailsDto> getListOfUsers();
}
