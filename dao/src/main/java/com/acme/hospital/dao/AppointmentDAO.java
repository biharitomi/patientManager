package com.acme.hospital.dao;

import java.util.Collection;
import java.util.Date;

import com.acme.hospital.domain.Appointment;
import com.acme.hospital.domain.Doctor;
import com.acme.hospital.domain.Patient;

public interface AppointmentDAO {

	public void persistAppointment(Appointment appointment);

	public void updateAppointment(Appointment appointment);

	public void deleteAppointment(Appointment appointment);

	public Collection<Appointment> getDoctorAppointments(Doctor doctor);

	public Appointment getDoctorAppointmentByDate(Doctor doctor, Date date);
	
	public Collection<Appointment> getDoctorAllAppointmentsFromDate(Doctor doctor, Date from, Patient patient);
	
	public Collection<Appointment> getDoctorAllAppointmentsBetween(Doctor doctor, Date start, Date end);
}
