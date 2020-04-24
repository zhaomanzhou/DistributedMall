package com.zmz121.model.po;

import lombok.*;

@Data
public class TaskUser {
    private Long id;

    private Long taskId;

    private Long publicUserId;

    private Long getUserId;

    //0发布没人接
    //1有人接
    //2确认送达
    //2取消订单
    private Integer publicStatus;

    //0 进行中
    //1 确认送达
    private Integer getStatus;

}