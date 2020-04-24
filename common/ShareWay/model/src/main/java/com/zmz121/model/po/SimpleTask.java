package com.zmz121.model.po;

import lombok.Data;

import java.util.Date;

@Data
public class SimpleTask
{
    private Long id;

    private Long publicUserId;

    //south和north
    private String school;

    private String title;
    private String destination;
    private String productLocation;
    private Integer isDaigou;
    private Integer daigouPrice;
    private Integer taskPrice;
    private Date publicDate;

}
