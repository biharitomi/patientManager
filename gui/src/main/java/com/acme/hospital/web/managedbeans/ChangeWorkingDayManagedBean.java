package com.acme.hospital.web.managedbeans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name="changeWorkingDayManagedBean")
@RequestScoped
public class ChangeWorkingDayManagedBean {
	private static Logger logger=LoggerFactory.getLogger(ChangeWorkingDayManagedBean.class);
	
	private Date sourceWorkingDay=new Date();
	private Date targetWrokingDay=new Date();
	
	public void changeWorkingDayEvent(){
		logger.info("changeWorkingDayEvent triggerd with sourceDate: "+sourceWorkingDay+" targetDate: "+targetWrokingDay);
	}
	
	public Date getSourceWorkingDay() {
		return sourceWorkingDay;
	}
	public void setSourceWorkingDay(Date sourceWorkingDay) {
		this.sourceWorkingDay = sourceWorkingDay;
	}
	public Date getTargetWrokingDay() {
		return targetWrokingDay;
	}
	public void setTargetWrokingDay(Date targetWrokingDay) {
		this.targetWrokingDay = targetWrokingDay;
	}
}
