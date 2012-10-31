package com.acme.hospital.service;

import java.util.Collection;
import java.util.Date;

import com.acme.hospital.domain.Appointment;
import com.acme.hospital.domain.Doctor;
import com.acme.hospital.domain.Patient;
import com.acme.hospital.dto.NeighborDates;

public interface AppointmentFacade {

	public boolean createAppointment(Doctor doctor, Patient patient, Date date);

	public NeighborDates getFreeNeighborDates(Doctor doctor, Date date);

	public boolean changeAppointmentDate(Appointment appointment, Date date);

	public void changeWorkingDay(Doctor doctor, Date sourceDate, Date targetDay);

	public Collection<Appointment> getDoctorAllAppointments(Doctor doctor);

	public Appointment getDoctorAppointmentByDate(Doctor doctor, Date date);
	
	public Collection<Patient> getAllPatients();
	
	public Doctor getDoctorByName(String doctorName);
}
