package com.zmz121;


import com.zmz121.dao.TaskMapper;
import com.zmz121.dao.TaskUserMapper;
import com.zmz121.dao.UserMapper;
import com.zmz121.model.po.*;
import com.zmz121.model.vo.DetailTask;
import com.zmz121.model.vo.SimpleTaskVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.DateFormter;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService
{
    @Autowired
    private TaskMapper taskMapper;


    @Autowired
    private TaskUserMapper taskUserMapper;

    @Autowired
    private UserMapper userMapper;

    private DateFormter formter = new DateFormter();

    @Transactional
    public void addTask(Task task)
    {
        taskMapper.insertSelective(task);
        TaskUser taskUser = new TaskUser();
        taskUser.setPublicUserId(task.getPublicUserId());
        taskUser.setTaskId(task.getId());
        taskUser.setPublicStatus(0);
        taskUserMapper.insertSelective(taskUser);
    }


    public List<SimpleTaskVo> queryAllSimple()
    {
        return taskMapper.queryAllSimpe().stream()
                .map(this::changToVo).collect(java.util.stream.Collectors.toList());
    }

    private SimpleTaskVo changToVo(SimpleTask task)
    {
        SimpleTaskVo taskVo = new SimpleTaskVo();
        BeanUtils.copyProperties(task, taskVo);
        if(task.getIsDaigou() == 0)
        {
            taskVo.setIsDaigou(false);
        }else
        {
            taskVo.setIsDaigou(true);
        }

        taskVo.setPublicDate(formter.fomat(task.getPublicDate()));
        return taskVo;
    }


    public Task queryById(Long id)
    {
        Task task = taskMapper.selectByPrimaryKey(id);
        return task;
    }




    public List<DetailTask> queryMyPublic(Long id)
    {
        List<Task> tasks = taskMapper.selectByPublicUserId(id);
        List<TaskUser> taskUsers = taskUserMapper.selectByPublicUserId(id);
        List<DetailTask> detailTaskList = new ArrayList<>();
        for(Task t: tasks)
        {
            DetailTask detailTask = new DetailTask();
            BeanUtils.copyProperties(t, detailTask);
            for(TaskUser taskUser: taskUsers)
            {
                if(taskUser.getTaskId().equals(t.getId()))
                {
                    detailTask.setPublicStatus(taskUser.getPublicStatus());
                    if(taskUser.getGetStatus() != null)
                    {
                        detailTask.setGetStatus(taskUser.getGetStatus());
                        User user = userMapper.selectByPrimaryKey(taskUser.getGetUserId());
                        detailTask.setGetUserName(user.getNickname());
                    }
                    break;
                }
            }
            detailTaskList.add(detailTask);

        }
        return detailTaskList;
    }


    public List<GetTask> selectBuGetUserId(Long id)
    {
        return taskMapper.selectBuGetUserId(id);
    }



}
