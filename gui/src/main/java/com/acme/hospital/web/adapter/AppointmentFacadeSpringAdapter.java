package com.acme.hospital.web.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.hospital.domain.Patient;
import com.acme.hospital.service.AppointmentFacade;

@Service("serviceAdapter")
public class AppointmentFacadeSpringAdapter {
	
	@Autowired
	private AppointmentFacade appointmentFacade;
	
	public List<Patient> getAllPatients(){
		return (List<Patient>) appointmentFacade.getAllPatients();
	}
	
	public AppointmentFacade getAppointmentFacade() {
		return appointmentFacade;
	}

	public void setAppointmentFacade(AppointmentFacade appointmentFacade) {
		this.appointmentFacade = appointmentFacade;
	}
}
