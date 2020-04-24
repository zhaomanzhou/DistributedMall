package com.zmz121.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SimpleTaskVo
{
    private Long id;

    private Long publicUserId;

    //south和north
    private String school;

    private String title;
    private String destination;
    private String productLocation;
    private Boolean isDaigou;
    private Integer daigouPrice;
    private Integer taskPrice;
    private String publicDate;

}
