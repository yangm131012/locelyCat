package com.yangm.dao.infomation;

import java.util.List;

import com.yangm.pojo.infomation.Cat;

public interface CatDao {
    int deleteByPrimaryKey(Long id);

    int insert(Cat record);

    int insertSelective(Cat record);

    Cat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Cat record);

    int updateByPrimaryKey(Cat record);

	List<Cat> findCatsByCondition(Cat cat);
}