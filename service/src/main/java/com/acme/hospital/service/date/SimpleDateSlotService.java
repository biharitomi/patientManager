package com.acme.hospital.service.date;

import java.util.Date;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.acme.hospital.dao.AppointmentDAO;
import com.acme.hospital.domain.Doctor;
import com.acme.hospital.dto.NeighborDates;

@Service
public class SimpleDateSlotService implements DateSlotService {

	@Autowired
	@Qualifier("hibernateAppointmentDao")
	private AppointmentDAO appointmentDAO;

	@Override
	public boolean isSlotFree(Doctor doctor, Date date) {
		boolean result = false;
		try {
			appointmentDAO.getDoctorAppointmentByDate(doctor, date);
		} catch (NoResultException e) {
			result = true;
		}
		return result;
	}

	@Override
	public NeighborDates findFreeNeighborSlot(Doctor doctor, Date date) {
		return null;
	}

	public AppointmentDAO getAppointmentDAO() {
		return appointmentDAO;
	}

	public void setAppointmentDAO(AppointmentDAO appointmentDAO) {
		this.appointmentDAO = appointmentDAO;
	}

	
}
