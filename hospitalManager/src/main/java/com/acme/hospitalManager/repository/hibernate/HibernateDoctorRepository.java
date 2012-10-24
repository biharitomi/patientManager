package com.acme.hospitalManager.repository.hibernate;

import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acme.hospital.domain.Doctor;
import com.acme.hospitalManager.repository.DoctorRepository;

@Repository
public class HibernateDoctorRepository implements DoctorRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public Doctor getDoctorById(long id) {
		Doctor doctor;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria crit=session.createCriteria(Doctor.class).add(Restrictions.eq("id", id));
		List<Doctor> result=(List<Doctor>)crit.list();
		
		if(result.isEmpty()){
			throw new NoResultException("Doctor not found with id: "+id);
		}
		else{
			doctor=result.get(0);
		}
		
		session.getTransaction().commit();
		session.close();
		
		return doctor;
	}

	@SuppressWarnings("unchecked")
	public Doctor getDoctorByName(String name) {
		Doctor doctor;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria crit=session.createCriteria(Doctor.class).add(Restrictions.eq("name",name));
		List<Doctor> result=(List<Doctor>)crit.list();
		
		if(result.isEmpty()){
			throw new NoResultException("Doctor not found with name: "+name);
		}
		else{
			doctor=result.get(0);
		}
		
		session.getTransaction().commit();
		session.close();
		
		return doctor;
	}

	@SuppressWarnings("unchecked")
	public Collection<Doctor> getAllDoctor() {
		List<Doctor> result;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria crit=session.createCriteria(Doctor.class);
		result=(List<Doctor>)crit.list();
		
		session.getTransaction().commit();
		session.close();
		
		return result;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}
