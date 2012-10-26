package com.acme.hospital.service.appointment;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.acme.hospital.dao.AppointmentDAO;
import com.acme.hospital.domain.Appointment;
import com.acme.hospital.domain.Doctor;
import com.acme.hospital.domain.Patient;

@Service
public class SimpleAppointmentService implements AppointmentService {
	
	@Autowired
	@Qualifier("hibernateAppointmentDao")
	AppointmentDAO appointmentDAO;

	@Override
	public void createAppointment(Doctor doctor, Patient patient, Date date) {
		Appointment appointment=new Appointment();
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		appointment.setDate(date);
		
		appointmentDAO.persistAppointment(appointment);
	}

	@Override
	public void changeAppointmentDate(Appointment appointment, Date newDate) {
		appointment.setDate(newDate);
		
		appointmentDAO.updateAppointment(appointment);
	}

	public AppointmentDAO getAppointmentDAO() {
		return appointmentDAO;
	}

	public void setAppointmentDAO(AppointmentDAO appointmentDAO) {
		this.appointmentDAO = appointmentDAO;
	}
}
