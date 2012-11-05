package com.acme.hospital.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.acme.hospital.domain.Appointment;
import com.acme.hospital.domain.Doctor;
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
	
	@Mock
	private Doctor doctor;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		underTest=new SimpleAppointmentFacade();
	}
	
	@Test
	public void appointmentServiceGetterSetterBehaviourTest(){
		underTest.setAppointmentService(appointmentService);
		Assert.assertEquals(appointmentService, underTest.getAppointmentService());
	}
	
	@Test
	public void dateSlotServiceGetterSetterBehaviourTest(){
		underTest.setDateSlotService(dateSlotService);
		Assert.assertEquals(dateSlotService, underTest.getDateSlotService());
	}
	
	@Test
	public void patientRepositoryGetterSetterBehaviourTest(){
		underTest.setPatientRepository(patientRepository);
		Assert.assertEquals(patientRepository, underTest.getPatientRepository());
	}
	
	@Test
	public void doctorRepositoryGetterSetterBehaviourTest(){
		underTest.setDoctorRepository(doctorRepository);
		Assert.assertEquals(doctorRepository, underTest.getDoctorRepository());
	}
	
	@Test
	public void getDoctorAllAppointmentsTestShouldReturnProperly(){
		//GIVEN
		Collection<Appointment> appointments=new ArrayList<Appointment>();
		underTest.setAppointmentService(appointmentService);
		BDDMockito.given(appointmentService.getDoctorAllAppointments(doctor)).willReturn(appointments);
		//WHEN
		Collection<Appointment> result=underTest.getDoctorAllAppointments(doctor);
		//THEN
		Mockito.verify(appointmentService).getDoctorAllAppointments(doctor);
		Assert.assertEquals(appointments, result);
	}
	
	@Test
	public void getDoctorAllAppointmentsTestInCaseNoResultExceptionShouldReturnProperly(){
		//GIVEN
		Collection<Appointment> appointments=new ArrayList<Appointment>();
		underTest.setAppointmentService(appointmentService);
		BDDMockito.given(appointmentService.getDoctorAllAppointments(doctor)).willThrow(new NoResultException());
		//WHEN
		Collection<Appointment> result=underTest.getDoctorAllAppointments(doctor);
		//THEN
		Mockito.verify(appointmentService).getDoctorAllAppointments(doctor);
		Assert.assertEquals(appointments, result);
	}
	
	
}
