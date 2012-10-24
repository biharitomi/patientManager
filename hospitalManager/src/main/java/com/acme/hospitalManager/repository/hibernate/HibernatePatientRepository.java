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

import com.acme.hospital.domain.Patient;
import com.acme.hospitalManager.repository.PatientRepository;

@Repository
public class HibernatePatientRepository implements PatientRepository {
//	private static Logger logger=LoggerFactory.getLogger(HibernatePatientRepository.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@SuppressWarnings("unchecked")
	public Patient getPatientById(long id) {
		Patient patient;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria crit=session.createCriteria(Patient.class).add(Restrictions.eq("id", id));
		List<Patient> result=(List<Patient>)crit.list();
		
		if(result.isEmpty()){
			throw new NoResultException("Patient not found with id: "+id);
		}
		else{
			patient=result.get(0);
		}
		
		session.getTransaction().commit();
		session.close();
		
		return patient;
	}
	
	@SuppressWarnings("unchecked")
	public Patient getPatientByName(String name) {
		Patient patient;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria crit=session.createCriteria(Patient.class).add(Restrictions.eq("name", name));
		List<Patient> result=(List<Patient>)crit.list();
		
		if(result.isEmpty()){
			throw new NoResultException("Patient not found with name: "+name);
		}
		else{
			patient=result.get(0);
		}
		
		session.getTransaction().commit();
		session.close();
		
		return patient;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Patient> getAllPatient() {
		List<Patient> patients;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		patients=(List<Patient>)session.createQuery("from Patient").list();
		
		session.getTransaction().commit();
		session.close();
		
		return patients;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
