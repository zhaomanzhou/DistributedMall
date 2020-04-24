package com.zmz121.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DetailTask
{
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



    private String getUserName;

    //0发布没人接
    //1有人接
    //2确认送达
    //2取消订单
    private Integer publicStatus;

    //0 进行中
    //1 确认送达
    private Integer getStatus;
}
