package com.yangm.service.infomation.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangm.dao.infomation.CatDao;
import com.yangm.pojo.infomation.Cat;
import com.yangm.service.infomation.CatService;

@Service
public class CatServiceImpl implements CatService {
	private static final Logger log = LoggerFactory.getLogger("adminLogger");
	@Autowired
	private CatDao catDao;

	@Override
	public String findCatsByCondition(Cat cat) {
		// TODO Auto-generated method stub
		PageHelper.startPage(cat.getPageNumber(), cat.getPageSize());
		List<Cat> pageInfo = catDao.findCatsByCondition(cat);
		PageInfo<Cat> pageInfos = new PageInfo<>(pageInfo);
		JSONObject result = new JSONObject();
		result.put("rows", pageInfo);
		result.put("total", pageInfos.getTotal());
		log.debug(result.toJSONString());
		return result.toJSONString();
	}

}
