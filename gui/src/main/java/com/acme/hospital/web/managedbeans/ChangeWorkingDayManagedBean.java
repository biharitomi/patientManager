package com.acme.hospital.web.managedbeans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acme.hospital.domain.Doctor;
import com.acme.hospital.service.AppointmentFacade;

@ManagedBean(name="changeWorkingDayManagedBean")
@RequestScoped
public class ChangeWorkingDayManagedBean {
	private static Logger logger=LoggerFactory.getLogger(ChangeWorkingDayManagedBean.class);
	
	private Date sourceWorkingDay=new Date();
	private Date targetWrokingDay=new Date();
	private Doctor loggedInDoctor;
	
	@ManagedProperty(value = "#{simpleAppointmentFacade}")
	private AppointmentFacade appointmentFacade;
	
	@ManagedProperty(value = "#{LoginManagedBean}")
	private LoginManagedBean loginManagedBean;
	
	public void changeWorkingDayEvent(){
		String loggedInDoctorName = loginManagedBean.getLoggedInUser();
		loggedInDoctor = appointmentFacade.getDoctorByName(loggedInDoctorName);
		logger.info("changeWorkingDayEvent triggerd with sourceDate: "+sourceWorkingDay+" targetDate: "+targetWrokingDay);
		appointmentFacade.changeWorkingDay(loggedInDoctor, sourceWorkingDay, targetWrokingDay);
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

	public AppointmentFacade getAppointmentFacade() {
		return appointmentFacade;
	}

	public void setAppointmentFacade(AppointmentFacade appointmentFacade) {
		this.appointmentFacade = appointmentFacade;
	}

	public LoginManagedBean getLoginManagedBean() {
		return loginManagedBean;
	}

	public void setLoginManagedBean(LoginManagedBean loginManagedBean) {
		this.loginManagedBean = loginManagedBean;
	}
	
}
