package com.yangm.mongodb.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.yangm.mongodb.dao.SysLogsMongoDao;
import com.yangm.pojo.system.SysLogs;
import com.yangm.utils.GeneralUtils;
@Repository
public class SysLogsMongoDaoImpl implements SysLogsMongoDao {

	@Autowired
    private MongoTemplate mongoTemplate;
	@Override
	public List<SysLogs> findSysLogs(SysLogs sysLogs) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        String userName = sysLogs.getUserName();
        if (!GeneralUtils.isNullOrZeroLenght(userName)) {
            query.addCriteria(criteria.where("userName").is(userName));
        }


        List<SysLogs> sysLogsList = mongoTemplate.find(
                query.skip(
                        (sysLogs.getPageNumber() -1) * sysLogs.getPageSize()).limit(sysLogs.getPageSize()
                        ).with(new Sort(Sort.Direction.DESC, "_id")),
                SysLogs.class);
        return sysLogsList;
	}

	@Override
	public void saveSysLogs(SysLogs sysLogs) {
		mongoTemplate.save(sysLogs);
		
	}

	@Override
	public int sysLogsTotal(SysLogs sysLogs) {
		// TODO Auto-generated method stub
        Query query = new Query();
        Criteria criteria = new Criteria();
        String userName = sysLogs.getUserName();
        if (!GeneralUtils.isNullOrZeroLenght(userName)) {
            query.addCriteria(criteria.where("userName").is(userName));
        }



        List<SysLogs> sysLogsList = mongoTemplate.find(query, SysLogs.class);

        if (GeneralUtils.isNullOrZeroSize(sysLogsList)) {
            return 0;
        }
        return sysLogsList.size();
	}

}
