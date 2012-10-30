package com.acme.hospital.web.managedbeans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "createAppointmentMB")
@SessionScoped
public class CreateAppointmentManagedBean {

	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
