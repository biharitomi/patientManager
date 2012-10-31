package com.acme.hospital.service;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.acme.hospital.service.appointment.AppointmentService;
import com.acme.hospital.service.date.DateSlotService;
import com.acme.hospitalManager.repository.DoctorRepository;
import com.acme.hospitalManager.repository.PatientRepository;

public class SimpleAppointmentFacadeTest {
	private SimpleAppointmentFacade underTest;
	
	@Mock
	private AppointmentService appointmentService;
	
	@Mock
	private DateSlotService dateSlotService;
	
	@Mock
	private PatientRepository patientRepository;
	
	@Mock
	private DoctorRepository doctorRepository;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		underTest=new SimpleAppointmentFacade();
	}
	
	
}
