package com.yangm.pojo.base;

import java.io.Serializable;

public class BaseEntity <ID extends Serializable> implements Serializable{

private static final long serialVersionUID = 2054813493011812469L;
	
	protected static final int DEFAULT_PAGENO = 1;
	protected static final int DEFAULT_PAGE = 10;

	//private ID id;
	private String sortOrder;
	private String sortName;
	private int pageNumber = DEFAULT_PAGENO;
	private int pageSize = DEFAULT_PAGE;

	/*
	 * public ID getId() { return id; }
	 * 
	 * public void setId(ID id) { this.id = id; }
	 */

	public String getSortOrder() {
		return sortOrder;
	}
	
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public String getSortName() {
		return sortName;
	}
	
	public void setSortName(String sortName) {
		// 判断排序字段是否为空
		if(sortName != null && sortName.length() > 0) {
			String resultString = "";
			
			// 循环遍历每一个字母
			for(int i = 0; i < sortName.length(); i++) {
				String letterString = sortName.substring(i,  i + 1);
				
				// 判断是否是大写字母，是的话，添加“_”
				if (letterString != null && letterString.equals(letterString.toUpperCase())) {
					resultString += "_";
				}
				resultString += letterString;
			}
			this.sortName = resultString;
		} else {
			this.sortName = sortName;
		}
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
