package com.acme.hospital.web.managedbeans;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.acme.hospital.domain.Patient;
import com.acme.hospital.web.adapter.AppointmentFacadeSpringAdapter;

@ManagedBean(name = "createAppointmentMB")
@SessionScoped
public class CreateAppointmentManagedBean {
	
	@ManagedProperty(value = "#{serviceAdapter}")
	private AppointmentFacadeSpringAdapter afsa;
	
	private Date date;
	
	private Patient selectedPatient;
	
	private List<Patient> allPatients;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<Patient> getAllPatients(){
		this.allPatients=afsa.getAllPatients();
		return this.allPatients;
	}

	public Patient getSelectedPatient() {
		return selectedPatient;
	}

	public void setSelectedPatient(Patient selectedPatient) {
		this.selectedPatient = selectedPatient;
	}

	public AppointmentFacadeSpringAdapter getAfsa() {
		return afsa;
	}

	public void setAfsa(AppointmentFacadeSpringAdapter afsa) {
		this.afsa = afsa;
	}
	
	
}
