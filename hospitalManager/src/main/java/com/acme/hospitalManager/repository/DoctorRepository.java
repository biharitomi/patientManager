package com.acme.hospitalManager.repository;

import java.util.Collection;

import com.acme.hospital.domain.Doctor;

public interface DoctorRepository {
	public Doctor getDoctorById(long id);

	public Doctor getDoctorByName(String name);

	public Collection<Doctor> getAllDoctor();
}
