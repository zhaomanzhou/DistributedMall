package com.zmz121.Controller;


import com.zmz121.TaskService;
import com.zmz121.model.po.Task;
import com.zmz121.model.vo.SimpleTaskVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import util.CommonReturnType;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/task")
public class TaskController
{
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private TaskService taskService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/add")
    public CommonReturnType addTask(Task task)
    {
        task.setProductLocation(request.getParameter("product_location"));
        task.setTaskPrice(Integer.parseInt(request.getParameter("task_price")));
        if(request.getParameter("is_daigou").equals("true"))
        {
            task.setIsDaigou(1);
            task.setDaigouPrice(Integer.parseInt(request.getParameter("daigou_price")));
        }else
        {
            task.setIsDaigou(0);
        }

        log.info("---------");
        log.info("task: " + task);

        taskService.addTask(task);
        return CommonReturnType.ok();

    }

    @GetMapping("/all/simple")
    public CommonReturnType queryAllSimple()
    {
        List<SimpleTaskVo> simpleTaskVos = taskService.queryAllSimple();
        return CommonReturnType.ok(simpleTaskVos);
    }

    @GetMapping("/{id}")
    public CommonReturnType queryById(@PathVariable Long id)
    {
        log.info("query task id " + id);
        Task task = taskService.queryById(id);
        return CommonReturnType.ok(task);
    }
}
