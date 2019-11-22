package com.mmall.mapper;

import com.mmall.bean.po.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    Admin selectByUser(String user);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}