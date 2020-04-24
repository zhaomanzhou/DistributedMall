package com.zmz121;


import com.zmz121.dao.TaskUserMapper;
import com.zmz121.model.po.TaskUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskUserService
{

    @Autowired
    private TaskUserMapper taskUserMapper;

    @Transactional
    public void updateTaskByAddGetUser(Long taskId, Long getUserId)
    {
        taskUserMapper.updateTaskByAddGetUser(taskId, getUserId);
    }


    public void changePublicStatus(Long taskId, Integer publicStatus)
    {
        TaskUser taskUser = new TaskUser();
        taskUser.setTaskId(taskId);
        taskUser.setPublicStatus(publicStatus);
        taskUserMapper.updateSelective(taskUser);
    }

    public void changeGetStatus(Long taskId, Integer getStatus)
    {
        TaskUser taskUser = new TaskUser();
        taskUser.setTaskId(taskId);
        taskUser.setGetStatus(getStatus);
        taskUserMapper.updateSelective(taskUser);
    }


}
