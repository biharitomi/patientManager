package com.acme.hospital.dao.hibernate;

import java.util.Collection;
import java.util.Date;

import com.acme.hospital.dao.AppointmentDAO;
import com.acme.hospital.domain.Appointment;
import com.acme.hospital.domain.Doctor;

public class HibernateAppointmentDao implements AppointmentDAO {

	@Override
	public void persistAppointment(Appointment appointment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAppointment(Appointment appointment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAppointment(Appointment appointment) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Appointment> getDoctorAppointments(Doctor doctor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appointment getDoctorAppointmentByDate(Doctor doctor, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

}
