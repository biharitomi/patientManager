package com.acme.hospital.dao;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acme.hospital.dao.hibernate.HibernateAppointmentDao;
import com.acme.hospital.domain.Appointment;
import com.acme.hospital.domain.Doctor;
import com.acme.hospital.domain.Patient;

public class TestBootStrap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/Spring/*.xml");
		AppointmentDAO appointmentDAO=ctx.getBean(HibernateAppointmentDao.class);

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
		
		Appointment a1=new Appointment();
		a1.setDoctor(d1);
		a1.setPatient(p1);
		a1.setDate(new Date());
		
		Appointment a2=new Appointment();
		a2.setDoctor(d1);
		a2.setPatient(p2);
		a2.setDate(new Date());
		
		appointmentDAO.persistAppointment(a1);
		appointmentDAO.persistAppointment(a2);
		
		System.out.println(appointmentDAO.getDoctorAppointments(d1));
		try{
			System.out.println(appointmentDAO.getDoctorAppointments(d2));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		appointmentDAO.deleteAppointment(a2);
		a1.setDate(new Date(100000L));
		appointmentDAO.updateAppointment(a1);
		System.out.println(appointmentDAO.getDoctorAppointments(d1));
	}

}
