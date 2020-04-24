package com.zmz121.model.po;

import lombok.Data;

@Data
public class GetTask
{
    private Long taskId;
    private Integer publicStatus;
    private Integer getStatus;
    private String title;
    private Integer isDaigou;
    private Integer daigouPrice;
    private Integer taskPrice;
}
