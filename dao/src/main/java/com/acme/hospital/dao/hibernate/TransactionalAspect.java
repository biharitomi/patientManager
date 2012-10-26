package com.acme.hospital.dao.hibernate;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
//@Component
public class TransactionalAspect {
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Around("execution(* com.acme.hospital.dao.hibernate.HibernateAppointmentDao.persistAppointment(..))")
	public void beforeTransaction(ProceedingJoinPoint joinpoint){
		System.out.println("AS*PECTE**************");
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		try{
			joinpoint.proceed();
			session.getTransaction().commit();
		}catch(Throwable ex){
			session.getTransaction().rollback();
		}
		session.close();
		System.out.println("AS*PECTE**************KIIIIIIIIIIIIIIIII");
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
}
