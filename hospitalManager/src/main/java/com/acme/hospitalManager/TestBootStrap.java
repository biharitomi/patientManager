package com.acme.hospitalManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acme.hospital.domain.Doctor;
import com.acme.hospital.domain.Patient;
import com.acme.hospitalManager.repository.hibernate.HibernateDoctorRepository;
import com.acme.hospitalManager.repository.hibernate.HibernatePatientRepository;

public class TestBootStrap {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/Spring/*.xml");
		HibernatePatientRepository hpr = ctx
				.getBean(HibernatePatientRepository.class);
		System.out.println(hpr.getPatientById(1L).toString());
		System.out.println(hpr.getPatientByName("Jamaar").toString());
		for (Patient p : hpr.getAllPatient()) {
			System.out.println(p.toString());
		}

		HibernateDoctorRepository hdr = ctx
				.getBean(HibernateDoctorRepository.class);
		System.out.println(hdr.getDoctorByName("Molly"));
		System.out.println(hdr.getDoctorById(1L));
		for (Doctor d : hdr.getAllDoctor()) {
			System.out.println(d);
		}

	}

}
