package com.acme.hospital.domain;

import junit.framework.Assert;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Before;
import org.junit.Test;

public class PatientTest {
	private Patient underTest;

	@Before
	public void setUp() {
		underTest = new Patient();
	}

	@Test
	public void testGetterSetterBehaviourOfId() {
		underTest.setId(1L);
		Assert.assertEquals(1L, underTest.getId());
	}

	@Test
	public void testGetterSetterBehaviourOfName() {
		underTest.setName("test name");
		Assert.assertEquals("test name", underTest.getName());
	}

	@Test
	public void testGetterSetterBehaviourOfMobileNumber() {
		underTest.setMobileNumber("36");
		Assert.assertEquals("36", underTest.getMobileNumber());
	}
	
	@Test
	public void testEquals() {
		EqualsVerifier.forClass(Patient.class)
				.suppress(Warning.STRICT_INHERITANCE).verify();
	}
	
	@Test
	public void testToString() {
		underTest.setId(1L);
		underTest.setName("Patient");
		underTest.setMobileNumber("36");
		Assert.assertEquals("Patient [id=1, name=Patient, mobileNumber=36]", underTest.toString());
	}
}
