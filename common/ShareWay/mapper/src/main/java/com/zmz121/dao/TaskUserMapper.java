package com.zmz121.dao;

import com.zmz121.model.po.TaskUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskUser record);

    int insertSelective(TaskUser record);

    TaskUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskUser record);


    int updateSelective(TaskUser record);

    int updateByPrimaryKey(TaskUser record);

    int updateTaskByAddGetUser(@Param("task_id") Long taskId, @Param("get_user_id") Long getUserId);

    List<TaskUser> selectByPublicUserId(Long id);



}