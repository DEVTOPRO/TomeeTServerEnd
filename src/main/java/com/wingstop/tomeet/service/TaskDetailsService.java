package com.wingstop.tomeet.service;

import org.springframework.http.ResponseEntity;

public interface TaskDetailsService {

    public ResponseEntity<?> getListOfUsersTasks();
}
