package com.acme.hospital.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acme.hospital.dao.hibernate.HibernateAppointmentDao;
import com.acme.hospital.domain.Appointment;
import com.acme.hospital.domain.Doctor;

public class TestBootStrap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/Spring/*.xml");
		AppointmentDAO appointmentDAO=ctx.getBean(HibernateAppointmentDao.class);

		Doctor d1=new Doctor();
		d1.setId(1L);
		d1.setName("Ahmed");
		
		Doctor d2=new Doctor();
		d2.setId(2L);
		d2.setName("Ahmed2");
		
		Appointment a1=new Appointment();
		a1.setDoctorId(1L);
		a1.setPatientId(1L);
		a1.setDate(new Date());
		
		Appointment a2=new Appointment();
		a2.setDoctorId(1L);
		a2.setPatientId(2L);
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
