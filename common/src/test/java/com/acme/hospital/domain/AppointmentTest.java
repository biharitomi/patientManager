package com.acme.hospital.domain;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppointmentTest {
	private Appointment underTest;
	
	@Before
	public void setUp(){
		underTest=new Appointment();
	}
	
	@Test
	public void testGetterSetterBehaviourOfId(){
		underTest.setId(1L);
		
		Assert.assertEquals(1L, underTest.getId());
	}
	
	@Test
	public void testGetterSetterBehaviourOfDate(){
		Date excepted=new Date();
		underTest.setDate(excepted);
		Assert.assertEquals(excepted, underTest.getDate());
	}
	
	@Test
	public void testGetterSetterBehaviourOfDoctor(){
		Doctor expected=new Doctor();
		underTest.setDoctor(expected);
		Assert.assertEquals(expected, underTest.getDoctor());
	}
	
	@Test
	public void testGetterSetterBehaviourOfPatient(){
		Patient expected=new Patient();
		underTest.setPatient(expected);
		Assert.assertEquals(expected, underTest.getPatient());
	}
}
