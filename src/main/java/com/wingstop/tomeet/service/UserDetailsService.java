package com.wingstop.tomeet.service;

import com.wingstop.tomeet.dto.ResponseObject;
import com.wingstop.tomeet.dto.UserDetailsDto;
import org.apache.commons.mail.EmailException;

public interface UserDetailsService {
    ResponseObject saveUser(UserDetailsDto userDetailsDto) throws EmailException;
}
