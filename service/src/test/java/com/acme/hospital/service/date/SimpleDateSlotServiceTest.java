package com.acme.hospital.service.date;

import java.util.Date;

import javax.persistence.NoResultException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.acme.hospital.dao.AppointmentDAO;
import com.acme.hospital.domain.Doctor;

public class SimpleDateSlotServiceTest {

	private SimpleDateSlotService underTest;

	@Mock
	private AppointmentDAO appointmentDAO;

	@Before
	public void setUp() {
		underTest = new SimpleDateSlotService();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testIsSlotFreeWhenSlotIsNotFreeShouldReturnFalse() {
		//GIVEN
		Doctor doctor = new Doctor();
		Date date = new Date();
		underTest.setAppointmentDAO(appointmentDAO);
		//WHEN
		boolean result = underTest.isSlotFree(doctor, date);
		//THEN
		Assert.assertEquals(false, result);
		BDDMockito.verify(appointmentDAO).getDoctorAppointmentByDate(doctor, date);
	}
	
	@Test
	public void testIsSlotFreeWhenSlotIsFreeShouldReturnFalse() {
		//GIVEN
		Doctor doctor = new Doctor();
		Date date = new Date();
		BDDMockito.given(appointmentDAO.getDoctorAppointmentByDate(doctor, date)).willThrow(new NoResultException());
		underTest.setAppointmentDAO(appointmentDAO);
		//WHEN
		boolean result = underTest.isSlotFree(doctor, date);
		//THEN
		Assert.assertEquals(true, result);
		BDDMockito.verify(appointmentDAO).getDoctorAppointmentByDate(doctor, date);
	}
}
