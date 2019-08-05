package com.yangm.pojo.system.dictionary;

import com.yangm.pojo.base.BaseEntity;

public class Dictionary extends BaseEntity<Long>{

	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Long serial;

	    private String paramCode;

	    private String paramName;

	    private String paramValue;

	    private String allowModify;

	    private String remark;

	    private Long paramSerial;

	    public Dictionary(Long serial, String paramCode, String paramName, String paramValue, String allowModify, String remark, Long paramSerial) {
	        this.serial = serial;
	        this.paramCode = paramCode;
	        this.paramName = paramName;
	        this.paramValue = paramValue;
	        this.allowModify = allowModify;
	        this.remark = remark;
	        this.paramSerial = paramSerial;
	    }

	    public Dictionary() {
	        super();
	    }

	    public Long getSerial() {
	        return serial;
	    }

	    public void setSerial(Long serial) {
	        this.serial = serial;
	    }

	    public String getParamCode() {
	        return paramCode;
	    }

	    public void setParamCode(String paramCode) {
	        this.paramCode = paramCode == null ? null : paramCode.trim();
	    }

	    public String getParamName() {
	        return paramName;
	    }

	    public void setParamName(String paramName) {
	        this.paramName = paramName == null ? null : paramName.trim();
	    }

	    public String getParamValue() {
	        return paramValue;
	    }

	    public void setParamValue(String paramValue) {
	        this.paramValue = paramValue == null ? null : paramValue.trim();
	    }

	    public String getAllowModify() {
	        return allowModify;
	    }

	    public void setAllowModify(String allowModify) {
	        this.allowModify = allowModify == null ? null : allowModify.trim();
	    }

	    public String getRemark() {
	        return remark;
	    }

	    public void setRemark(String remark) {
	        this.remark = remark == null ? null : remark.trim();
	    }

	    public Long getParamSerial() {
	        return paramSerial;
	    }

	    public void setParamSerial(Long paramSerial) {
	        this.paramSerial = paramSerial;
	    }
}
