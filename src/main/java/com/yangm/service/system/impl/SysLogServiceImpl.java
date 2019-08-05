package com.yangm.service.system.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yangm.mongodb.dao.SysLogsMongoDao;
import com.yangm.pojo.system.SysLogs;
import com.yangm.pojo.system.User;
import com.yangm.service.system.SysLogService;
import com.yangm.utils.GeneralUtils;
import com.yangm.utils.UserUtil;
@Service("SysLogService")
public class SysLogServiceImpl implements SysLogService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private SysLogsMongoDao sysLogsMongoDao;

	@Override
	public String findSysLogs(SysLogs sysLogs) {

        List<SysLogs> sysLogss = sysLogsMongoDao.findSysLogs(sysLogs);
        int total = sysLogsMongoDao.sysLogsTotal(sysLogs);
        JSONObject result = new JSONObject();
        result.put("rows", sysLogss);
        result.put("total", total);
        return result.toJSONString();
		
	}

	@Override
	public void saveSysLogs(SysLogs sysLogs) {
		
		User user = UserUtil.getCurrentUser();
		if (user == null || user.getSerial() == null) {
			return;
		}

		sysLogs.setUserName(user.getUserName());
		
		sysLogs.setCreateTime(GeneralUtils.getCurrentTimeStamp(null));
		
		sysLogsMongoDao.saveSysLogs(sysLogs);
		
	}

}
