package com.yangm.service.system;

import com.yangm.pojo.system.SysLogs;

public interface SysLogService {

	 String findSysLogs(SysLogs sysLogs);
	    
	    public void saveSysLogs(SysLogs sysLogs);
}
