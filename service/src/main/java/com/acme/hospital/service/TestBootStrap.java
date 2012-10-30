package com.acme.hospital.service;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acme.hospital.domain.Doctor;
import com.acme.hospital.domain.Patient;

public class TestBootStrap {
	
	public static void main(String[] args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/Spring/serviceApplicationContext.xml");
		
		AppointmentFacade af = ctx.getBean(AppointmentFacade.class);
		
		
		Doctor d1=new Doctor();
		d1.setId(1L);
		d1.setName("Antony");
		
		Doctor d2=new Doctor();
		d2.setId(2L);
		d2.setName("Patrick");
		
		Patient p1=new Patient();
		p1.setId(1L);
		p1.setName("Melissa");
		p1.setMobileNumber("+36301233233");
		
		Patient p2=new Patient();
		p2.setId(2L);
		p2.setName("Doris");
		p2.setMobileNumber("+36301233222");
		
		Date date = new Date();
//		Date date2 = new Date(6644L);
		
		System.out.println("Is it goooood?" + af.createAppointment(d1, p1, date));
		System.out.println("Is it goooood?" + af.createAppointment(d1, p1, date));
		try{
			System.out.println(af.getDoctorAllAppointments(d2));
		}catch(Throwable e){
			e.printStackTrace();
		}
		System.out.println(af.getDoctorAllAppointments(d1));
		
		System.out.println(af.getAllPatients());
		
	}
}
