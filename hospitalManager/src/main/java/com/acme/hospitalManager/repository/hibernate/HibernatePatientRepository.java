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
import com.acme.hospital.domain.Patient;
import com.acme.hospitalManager.repository.PatientRepository;

@Repository
public class HibernatePatientRepository implements PatientRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public Patient getPatientById(long id) {
		List<Patient> patients = getPatientsByCriteria("id", id);
		return patients.get(0);
	}

	public Patient getPatientByName(String name) {
		List<Patient> patients = getPatientsByCriteria("name", name);
		return patients.get(0);
	}

	public Collection<Patient> getAllPatient() {
		List<Patient> patients = getPatientsByCriteria(null, null);
		return patients;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private List<Patient> getPatientsByCriteria(String name, Object object) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria crit = session.createCriteria(Patient.class);

		if (name != null && object != null) {
			crit.add(Restrictions.eq(name, object));
		}

		@SuppressWarnings("unchecked")
		List<Patient> result = (List<Patient>) crit.list();

		if (result.isEmpty()) {
			throw new NoResultException(
					"No patient(s) found for the given criteria!");
		}

		session.getTransaction().commit();
		session.close();

		return result;
	}

}
