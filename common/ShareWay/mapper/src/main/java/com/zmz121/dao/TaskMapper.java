package com.zmz121.dao;

import com.zmz121.model.po.GetTask;
import com.zmz121.model.po.Task;
import com.zmz121.model.po.SimpleTask;

import java.util.List;

public interface TaskMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    List<SimpleTask> queryAllSimpe();

    List<Task> selectByPublicUserId(Long id);

    List<GetTask> selectBuGetUserId(Long id);
}