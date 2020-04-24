package com.zmz121.dao;

import com.zmz121.model.po.TaskUser;

public interface TaskUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskUser record);

    int insertSelective(TaskUser record);

    TaskUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskUser record);

    int updateByPrimaryKey(TaskUser record);
}