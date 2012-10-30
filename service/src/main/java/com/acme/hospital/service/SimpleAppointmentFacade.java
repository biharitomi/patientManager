package com.acme.hospital.service;

import java.util.Collection;
import java.util.Date;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.acme.hospital.domain.Appointment;
import com.acme.hospital.domain.Doctor;
import com.acme.hospital.domain.Patient;
import com.acme.hospital.dto.NeighborDates;
import com.acme.hospital.service.appointment.AppointmentService;
import com.acme.hospital.service.date.DateSlotService;
import com.acme.hospitalManager.repository.PatientRepository;

@Component("sipmleAppointmentFacade")
public class SimpleAppointmentFacade implements AppointmentFacade {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private DateSlotService dateSlotService;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	@Transactional(rollbackFor=NoResultException.class)
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

	@Override
	public Collection<Appointment> getDoctorAllAppointments(Doctor doctor) {
		return appointmentService.getDoctorAllAppointments(doctor);
	}

	@Override
	public Appointment getDoctorAppointmentByDate(Doctor doctor, Date date) {
		return appointmentService.getDoctorAppointmentByDate(doctor, date);
	}

	@Override
	public Collection<Patient> getAllPatients() {
		return patientRepository.getAllPatient();
	}

	public AppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	public DateSlotService getDateSlotService() {
		return dateSlotService;
	}

	public void setDateSlotService(DateSlotService dateSlotService) {
		this.dateSlotService = dateSlotService;
	}

	public PatientRepository getPatientRepository() {
		return patientRepository;
	}

	public void setPatientRepository(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

}
