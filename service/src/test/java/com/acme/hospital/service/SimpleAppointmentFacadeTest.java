package com.acme.hospital.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
import com.acme.hospital.domain.Patient;
import com.acme.hospital.dto.NeighborDates;
import com.acme.hospital.exception.ReservedAppointmentException;
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
	
	@Mock
	private Patient patient;
	
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
	
	@Test
	public void getFreeNeighborDatesTestShouldReturnProperly(){
		//GIVEN
		NeighborDates neighborDates=new NeighborDates();
		Date date=new Date();
		underTest.setDateSlotService(dateSlotService);
		BDDMockito.given(dateSlotService.findFreeNeighborSlot(doctor, date)).willReturn(neighborDates);
		//WHEN
		NeighborDates result=underTest.getFreeNeighborDates(doctor, date);
		//THEN
		Mockito.verify(dateSlotService).findFreeNeighborSlot(doctor, date);
		Assert.assertEquals(neighborDates, result);
	}
	
	@Test
	public void createAppointmentTestShouldReturnProperlyWhenTheDateIsFree(){
		//GIVEN
		Date date=new Date();
		underTest.setDateSlotService(dateSlotService);
		underTest.setAppointmentService(appointmentService);
		BDDMockito.given(dateSlotService.isSlotFree(doctor, date)).willReturn(true);
		BDDMockito.given(appointmentService.hasAppointmentInTheFutureWith(doctor, patient)).willReturn(false);
		//WHEN
		boolean result=underTest.createAppointment(doctor, patient, date);
		//THEN
		Mockito.verify(dateSlotService).isSlotFree(doctor, date);
		Mockito.verify(appointmentService).hasAppointmentInTheFutureWith(doctor, patient);
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void createAppointmentTestShouldReturnProperlyWhenTheDateIsNotFree(){
		//GIVEN
		Date date=new Date();
		underTest.setDateSlotService(dateSlotService);
		underTest.setAppointmentService(appointmentService);
		BDDMockito.given(dateSlotService.isSlotFree(doctor, date)).willReturn(false);
		BDDMockito.given(appointmentService.hasAppointmentInTheFutureWith(doctor, patient)).willReturn(false);
		//WHEN
		boolean result=underTest.createAppointment(doctor, patient, date);
		//THEN
		Mockito.verify(dateSlotService).isSlotFree(doctor, date);
		Mockito.verify(appointmentService).hasAppointmentInTheFutureWith(doctor, patient);
		Assert.assertEquals(false, result);
	}
	
	@Test(expected=ReservedAppointmentException.class)
	public void createAppointmentTestShouldThrowReservedAppointmentExceptionWhenPatientHasAppointmentInTheFuture(){
		//GIVEN
		Date date=new Date();
		underTest.setDateSlotService(dateSlotService);
		underTest.setAppointmentService(appointmentService);
		BDDMockito.given(dateSlotService.isSlotFree(doctor, date)).willReturn(true);
		BDDMockito.given(appointmentService.hasAppointmentInTheFutureWith(doctor, patient)).willReturn(true);
		//WHEN
		boolean result=underTest.createAppointment(doctor, patient, date);
		//THEN
		Mockito.verify(dateSlotService).isSlotFree(doctor, date);
		Mockito.verify(appointmentService).hasAppointmentInTheFutureWith(doctor, patient);
		Assert.assertEquals(true, result);
	}
}
