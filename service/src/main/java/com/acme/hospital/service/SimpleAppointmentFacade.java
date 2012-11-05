package com.acme.hospital.service;

import java.util.ArrayList;
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
import com.acme.hospital.exception.ReservedAppointmentException;
import com.acme.hospital.service.appointment.AppointmentService;
import com.acme.hospital.service.date.DateSlotService;
import com.acme.hospitalManager.repository.DoctorRepository;
import com.acme.hospitalManager.repository.PatientRepository;

@Component("simpleAppointmentFacade")
public class SimpleAppointmentFacade implements AppointmentFacade {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private DateSlotService dateSlotService;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	@Transactional(rollbackFor = NoResultException.class)
	public boolean createAppointment(Doctor doctor, Patient patient, Date date){
		boolean isFree = dateSlotService.isSlotFree(doctor, date);
		boolean hasAppointmentInTheFuture=appointmentService.hasAppointmentInTheFutureWith(doctor,patient);
		
		if (isFree && !hasAppointmentInTheFuture) {
			appointmentService.createAppointment(doctor, patient, date);
		}
		else if(hasAppointmentInTheFuture){
			throw new ReservedAppointmentException("The patient: "+patient+" already has an appointment with: "+doctor);
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
		Collection<Appointment> appointments;
		try {
		appointments = appointmentService.getDoctorAllAppointments(doctor);
		} catch(NoResultException e) {
			appointments = new ArrayList<Appointment>();
		}
		return appointments;
	}

	@Override
	public Appointment getDoctorAppointmentByDate(Doctor doctor, Date date) {
		return appointmentService.getDoctorAppointmentByDate(doctor, date);
	}

	@Override
	public Collection<Patient> getAllPatients() {
		return patientRepository.getAllPatient();
	}

	@Override
	public Doctor getDoctorByName(String doctorName) {
		return doctorRepository.getDoctorByName(doctorName);
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

	public DoctorRepository getDoctorRepository() {
		return doctorRepository;
	}

	public void setDoctorRepository(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}
}
