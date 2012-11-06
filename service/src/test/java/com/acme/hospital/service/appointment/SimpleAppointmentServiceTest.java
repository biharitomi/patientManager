package com.acme.hospital.service.appointment;

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

import com.acme.hospital.dao.AppointmentDAO;
import com.acme.hospital.domain.Appointment;
import com.acme.hospital.domain.Doctor;
import com.acme.hospital.domain.Patient;

public class SimpleAppointmentServiceTest {
	private SimpleAppointmentService underTest;
	
	@Mock
	private AppointmentDAO appointmentDAO;
	
	@Mock
	private Doctor doctor;
	
	@Mock
	private Patient patient;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		underTest=new SimpleAppointmentService();
		underTest.setAppointmentDAO(appointmentDAO);
	}
	
	@Test
	public void createAppointmentTestShouldReturnProperly(){
		//GIVEN
		Date date=new Date();
		
		Appointment appointment=new Appointment();
		appointment.setDate(date);
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		//WHEN
		underTest.createAppointment(doctor, patient, date);
		//THEN
		Mockito.verify(appointmentDAO).persistAppointment(appointment);
	}
	
	@Test
	public void updateAppointmentTestShouldReturnProperly(){
		//GIVEN
		Date date=new Date();
		Date newDate=new Date(30000L);
		
		Appointment appointment=new Appointment();
		appointment.setDate(date);
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		//WHEN
		underTest.changeAppointmentDate(appointment, newDate);
		appointment.setDate(newDate);
		//THEN
		Mockito.verify(appointmentDAO).updateAppointment(appointment);
	}
	
	@Test
	public void getDoctorAllAppointmentsTestShouldReturnProperly(){
		//GIVEN
		Collection<Appointment> appointments=new ArrayList<Appointment>();
		BDDMockito.given(appointmentDAO.getDoctorAppointments(doctor)).willReturn(appointments);
		//WHEN
		Collection<Appointment> result=underTest.getDoctorAllAppointments(doctor);
		//THEN
		Mockito.verify(appointmentDAO).getDoctorAppointments(doctor);
		Assert.assertEquals(appointments, result);
	}
	
	@Test(expected=NoResultException.class)
	public void getDoctorAllAppointmentsTestWhenNoResultsExistShouldThrowNoResultException(){
		//GIVEN
		BDDMockito.given(appointmentDAO.getDoctorAppointments(doctor)).willThrow(new NoResultException());
		//WHEN
		underTest.getDoctorAllAppointments(doctor);
		//THEN
		Mockito.verify(appointmentDAO).getDoctorAppointments(doctor);
	}
	
	@Test
	public void getDoctorAppointmentByDateTestShouldReturnProperly(){
		//GIVEN
		Date date=new Date(30000L);
		Appointment appointment=new Appointment();
		BDDMockito.given(appointmentDAO.getDoctorAppointmentByDate(doctor, date)).willReturn(appointment);
		//WHEN
		Appointment result=underTest.getDoctorAppointmentByDate(doctor, date);
		//THEN
		Mockito.verify(appointmentDAO).getDoctorAppointmentByDate(doctor, date);
		Assert.assertEquals(appointment, result);
	}
	
	@Test(expected=NoResultException.class)
	public void getDoctorAppointmentByDateTestWhenNoResultsExistShouldThrowNoResultException(){
		//GIVEN
		Date date=new Date(30000L);
		BDDMockito.given(appointmentDAO.getDoctorAppointmentByDate(doctor, date)).willThrow(new NoResultException());
		//WHEN
		underTest.getDoctorAppointmentByDate(doctor, date);
		//THEN
		Mockito.verify(appointmentDAO).getDoctorAppointmentByDate(doctor, date);
	}
	
	@Test
	public void hasAppointmentInTheFutureWithTestShouldReturnProperlyWhenPatientHasAppointmentInTheFuture(){
		//GIVEN
		Collection<Appointment> appointments=new ArrayList<Appointment>();
		BDDMockito.given(appointmentDAO.getDoctorAllAppointmentsFromDate(doctor, underTest.now, patient)).willReturn(appointments);
		//WHEN
		boolean result=underTest.hasAppointmentInTheFutureWith(doctor, patient);
		//THEN
		Mockito.verify(appointmentDAO).getDoctorAllAppointmentsFromDate(doctor, underTest.now, patient);
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void appointmentDAOGetterandSetterBehaviourTest(){
		underTest.setAppointmentDAO(appointmentDAO);
		Assert.assertEquals(appointmentDAO, underTest.getAppointmentDAO());
	}
}
