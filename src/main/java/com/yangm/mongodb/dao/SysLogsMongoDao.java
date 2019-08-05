package com.yangm.mongodb.dao;

import java.util.List;

import com.yangm.pojo.system.SysLogs;


public interface SysLogsMongoDao {

	 List<SysLogs> findSysLogs(SysLogs sysLogs);
	    
	    public void saveSysLogs(SysLogs sysLogs);
	    
	    int sysLogsTotal(SysLogs sysLogs);
}
