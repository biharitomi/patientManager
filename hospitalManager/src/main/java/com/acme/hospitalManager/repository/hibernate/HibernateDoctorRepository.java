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

	public Doctor getDoctorById(long id) {
		List<Doctor> doctors = getDoctorsByCriteria("id", id);
		return doctors.get(0);
	}

	@Override
	public Doctor getDoctorByName(String name) {
		List<Doctor> doctors = getDoctorsByCriteria("name", name);
		return doctors.get(0);
	}

	@Override
	public Collection<Doctor> getAllDoctor() {
		List<Doctor> doctors = getDoctorsByCriteria(null, null);
		return doctors;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private List<Doctor> getDoctorsByCriteria(String name, Object object) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria crit = session.createCriteria(Doctor.class);

		if (name != null && object != null) {
			crit.add(Restrictions.eq(name, object));
		}

		@SuppressWarnings("unchecked")
		List<Doctor> result = (List<Doctor>) crit.list();

		if (result.isEmpty()) {
			throw new NoResultException(
					"No doctor(s) found for the given criteria!");
		}

		session.getTransaction().commit();
		session.close();

		return result;
	}

}
