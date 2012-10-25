package com.acme.hospital.dao;

import java.util.Collection;
import java.util.Date;

import com.acme.hospital.domain.Appointment;
import com.acme.hospital.domain.Doctor;

public interface AppointmentDAO {

	public void persistAppointment(Appointment appointment);

	public void updateAppointment(Appointment appointment);

	public void deleteAppointment(Appointment appointment);

	public Collection<Appointment> getDoctorAppointments(Doctor doctor);

	public Appointment getDoctorAppointmentByDate(Doctor doctor, Date date);
}
