package com.yangm.dao.system.account.organization;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yangm.pojo.system.account.organization.Organization;


public interface OrganizationMapper {

	int deleteByPrimaryKey(Long id);

	int insert(Organization record);

	int insertSelective(Organization record);

	Organization selectByPrimaryKey(Long id);

	Organization selectById(Long id);

	int updateByPrimaryKeySelective(Organization record);

	int updateByPrimaryKey(Organization record);

	List<Organization> findOrganizationByCondition(Organization organization);

	String getParentHasLastChildCode(Long parentId);

	String getParentNum();

	List<Organization> selectByParentId(Long id);

	List<Organization> queryIdAndName();

	/**
	 * 根据parentId查找其子级机构
	 * 
	 * @param parentId
	 * @return
	 */
	List<Organization> findChildByParentId(@Param("parentId") Long parentId);
	
	/**
	 * 根据code模糊查询自身以及子级机构
	 * @param code
	 * @return
	 */
	List<Organization> findSelfAndChildByCode(@Param("code") String code);
	
	/**
	 * 根据code查询
	 * @param code
	 * @return
	 */
	Organization findByCode(@Param("code") String code);
}
