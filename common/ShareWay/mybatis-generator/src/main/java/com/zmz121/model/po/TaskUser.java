package com.zmz121.model.po;

public class TaskUser {
    private Long id;

    private Long taskId;

    private Long publicUserId;

    private Long getUserId;

    private Byte publicStatus;

    private Byte getStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getPublicUserId() {
        return publicUserId;
    }

    public void setPublicUserId(Long publicUserId) {
        this.publicUserId = publicUserId;
    }

    public Long getGetUserId() {
        return getUserId;
    }

    public void setGetUserId(Long getUserId) {
        this.getUserId = getUserId;
    }

    public Byte getPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(Byte publicStatus) {
        this.publicStatus = publicStatus;
    }

    public Byte getGetStatus() {
        return getStatus;
    }

    public void setGetStatus(Byte getStatus) {
        this.getStatus = getStatus;
    }
}