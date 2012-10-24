package com.acme.hospitalManager.repository;

import java.util.Collection;

import com.acme.hospital.domain.Patient;

public interface PatientRepository {
	public Patient getPatientById(long id);

	public Patient getPatientByName(String name);

	public Collection<Patient> getAllPatient();
}
