package com.zmz121.model.po;

import lombok.*;

import java.util.Date;

@Data
public class Task {
    //任务id
    private Long id;

    private Long publicUserId;

    //south和north
    private String school;

    private String title;

    private String content;

    private String tel;

    private String destination;

    private String productLocation;

    private String comment;

    //0否 1是
    private Integer isDaigou;

    private Integer daigouPrice;

    private Integer taskPrice;

    private Date publicDate;

}