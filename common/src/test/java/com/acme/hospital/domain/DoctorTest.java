package com.acme.hospital.domain;

import junit.framework.Assert;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Before;
import org.junit.Test;

public class DoctorTest {

	private Doctor underTest;

	@Before
	public void setUp() {
		underTest = new Doctor();
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
	public void testEquals() {
		EqualsVerifier.forClass(Doctor.class)
				.suppress(Warning.STRICT_INHERITANCE).verify();
	}
	
	@Test
	public void testToString() {
		underTest.setId(1L);
		underTest.setName("Doki");
		Assert.assertEquals("Doctor [id=1, name=Doki]", underTest.toString());
	}
}
