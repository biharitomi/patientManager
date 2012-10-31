package com.acme.hospital.domain;

import java.util.Date;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppointmentTest {
	private Appointment underTest;

	@Before
	public void setUp() {
		underTest = new Appointment();
	}

	@Test
	public void testGetterSetterBehaviourOfId() {
		underTest.setId(1L);
		Assert.assertEquals(1L, underTest.getId());
	}

	@Test
	public void testGetterSetterBehaviourOfDate() {
		Date excepted = new Date(11L);
		underTest.setDate(excepted);
		Assert.assertEquals(excepted, underTest.getDate());
	}

	@Test
	public void testGetterSetterBehaviourOfDoctor() {
		Doctor expected = new Doctor();
		underTest.setDoctor(expected);
		Assert.assertEquals(expected, underTest.getDoctor());
	}

	@Test
	public void testGetterSetterBehaviourOfPatient() {
		Patient expected = new Patient();
		underTest.setPatient(expected);
		Assert.assertEquals(expected, underTest.getPatient());
	}

	@Test
	public void testToString() {
		underTest.setDate(new Date(11L));
		underTest.setDoctor(new Doctor());
		underTest.setPatient(new Patient());
		underTest.setId(1L);
		Assert.assertEquals(
				"Appointment [id=1, date=Thu Jan 01 01:00:00 CET 1970,"
						+ " doctor=Doctor [id=0, name=null], "
						+ "patient=Patient [id=0, name=null, mobileNumber=null]]",
				underTest.toString());

	}

	@Test
	public void testEqualsContract() {
		EqualsVerifier.forClass(Appointment.class)
				.suppress(Warning.STRICT_INHERITANCE).verify();
	}
}
