package com.acme.hospital.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.acme.hospital.domain.Appointment;
import com.acme.hospital.domain.Doctor;
import com.acme.hospital.domain.Patient;
import com.acme.hospital.dto.NeighborDates;
import com.acme.hospital.service.appointment.AppointmentService;
import com.acme.hospital.service.date.DateSlotService;

@Component
public class SimpleAppointmentFacade implements AppointmentFacade {
	
	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	DateSlotService dateSlotService;
	
	@Override
	@Transactional
	public boolean createAppointment(Doctor doctor, Patient patient, Date date) {
		boolean isFree=false;
		isFree=dateSlotService.isSlotFree(doctor, date);
		
		if(isFree==true){
			appointmentService.createAppointment(doctor, patient, date);
		}
		
		return isFree;
	}

	@Override
	public NeighborDates getFreeNeighborDates(Doctor doctor, Date date) {
		return null;
	}

	@Override
	public boolean changeAppointmentDate(Appointment appointment, Date date) {
		return false;
	}

	@Override
	public void changeWorkingDay(Doctor doctor, Date sourceDate, Date targetDay) {

	}

}
