package com.wingstop.tomeet.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;



@Data
public class CommonModel {


    @Column(name="created_time")
    private Date createdTime;

    @Column(name = "updated_time")
    private Date updatedTime;

    @Column(name="status")
    private Short status;

    @PrePersist
    protected void onCreate() {
        createdTime = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedTime = new Date();
    }

}
