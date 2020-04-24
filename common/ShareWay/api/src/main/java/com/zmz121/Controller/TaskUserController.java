package com.zmz121.Controller;

import com.zmz121.TaskService;
import com.zmz121.TaskUserService;
import com.zmz121.model.po.GetTask;
import com.zmz121.model.vo.DetailTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.CommonReturnType;

import java.util.List;

@RestController
@RequestMapping("/api/taskuser")
public class TaskUserController
{


    @Autowired
    private TaskUserService taskUserService;

    @Autowired
    private TaskService taskService;

    @PostMapping("/get")
    public CommonReturnType getTask(@RequestParam("get_user_id") Long getUserId, @RequestParam("task_id") Long taskId)
    {
        taskUserService.updateTaskByAddGetUser(taskId, getUserId);

        return CommonReturnType.ok();
    }

    @GetMapping("/my/public/{id}")
    public CommonReturnType getMyPublic(@PathVariable("id") Long id)
    {
        List<DetailTask> detailTaskList = taskService.queryMyPublic(id);
        return CommonReturnType.ok(detailTaskList);
    }


    @GetMapping("/my/get/{id}")
    public CommonReturnType getMyGet(@PathVariable("id") Long id)
    {
        List<GetTask> getTasks = taskService.selectBuGetUserId(id);
        return CommonReturnType.ok(getTasks);
    }


    @PutMapping("/my/public/status/{taskId}")
    public CommonReturnType changeStatus(@PathVariable("taskId") Long taskId, @RequestParam("public_status") Integer publicStatus)
    {
        taskUserService.changePublicStatus(taskId, publicStatus);
        return CommonReturnType.ok();
    }

    @PutMapping("/my/get/status/{taskId}")
    public CommonReturnType changeGetStatus(@PathVariable("taskId") Long taskId, @RequestParam("get_status") Integer getStatus)
    {
        taskUserService.changeGetStatus(taskId, getStatus);
        return CommonReturnType.ok();
    }



}
