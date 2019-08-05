package com.yangm.dao.dictionary;

import java.util.List;

import com.yangm.pojo.system.dictionary.Dictionary;


public interface DictionaryMapper {

	int deleteByPrimaryKey(Long serial);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(Long serial);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
    
    List<Dictionary> selectList(Dictionary record);
    
    int check(Dictionary dictionary);

    List<Dictionary> checkUpdate(Dictionary dictionary);
	
	Dictionary selectParamName(Long serial);

	int checkParent(Long serial);
}
