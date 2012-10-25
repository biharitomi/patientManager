package com.acme.hospital.dao.hibernate;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acme.hospital.dao.AppointmentDAO;
import com.acme.hospital.domain.Appointment;
import com.acme.hospital.domain.Doctor;

@Repository
public class HibernateAppointmentDao implements AppointmentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void persistAppointment(Appointment appointment) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.saveOrUpdate(appointment);
		
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		persistAppointment(appointment);
	}

	@Override
	public void deleteAppointment(Appointment appointment) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.delete(appointment);
		
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Appointment> getDoctorAppointments(Doctor doctor) {
		List<Appointment> appointments;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		appointments=(List<Appointment>)session.createCriteria(Appointment.class).add(Restrictions.eq("doctor", doctor)).list();
		
		session.getTransaction().commit();
		session.close();
		
		if(appointments.isEmpty()){
			throw new NoResultException("Doesn't exist appointment with doctor: "+doctor.toString());
		}
		
		return appointments;
	}

	@Override
	public Appointment getDoctorAppointmentByDate(Doctor doctor, Date date) {
		Appointment appointment;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		appointment=(Appointment)session.createCriteria(Appointment.class).add(Restrictions.eq("doctor", doctor)).add(Restrictions.eq("date", date)).uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		
		if(appointment==null){
			throw new NoResultException("Doesn't exist appointment with doctor: "+doctor.toString()+" and with date: "+date);
		}
		
		return appointment;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}