package com.acme.hospital.service.appointment;

import java.util.Date;

import com.acme.hospital.domain.Appointment;
import com.acme.hospital.domain.Doctor;
import com.acme.hospital.domain.Patient;

public interface AppointmentService {

	public void createAppointment(Doctor doctor, Patient patient, Date date);

	public void changeAppointmentDate(Appointment appointment, Date newDate);
}