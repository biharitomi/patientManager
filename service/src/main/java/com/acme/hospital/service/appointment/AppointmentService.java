package com.acme.hospital.service.appointment;

import java.util.Date;

import com.acme.hospital.domain.Appointment;
import com.acme.hospital.domain.Doctor;
import com.acme.hospital.domain.Patient;

public interface AppointmentService {

	public boolean createAppointment(Doctor doctor, Patient patient, Date date);

	public boolean changeAppointmentDate(Appointment appointment, Date newDate);
}
