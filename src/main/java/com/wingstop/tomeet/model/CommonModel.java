package com.wingstop.tomeet.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;



@Data
@MappedSuperclass
public abstract class CommonModel {


    @Column(name="created_time")
    @CreationTimestamp
    private Date createdTime;

    @Column(name = "updated_time")
    @UpdateTimestamp
    private Date updatedTime;

    @Column(name="status")
    private Integer status;


}
