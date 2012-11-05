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
		NeighborDates nd = new NeighborDates();
		nd.setNextDate(new Date(date.getTime()));
		nd.setPreviousDate(new Date(date.getTime()));
		while (!isSlotFree(doctor, nd.getNextDate())) {
			nextQuarterHour(nd.getNextDate());
		}
		while (!isSlotFree(doctor, nd.getPreviousDate())) {
			previousQuarterHour(nd.getPreviousDate());
		}
		return nd;
	}

	private void nextQuarterHour(Date d) {
		d.setTime(d.getTime() + 1000 * 60 * 15);
	}

	private void previousQuarterHour(Date d) {
		d.setTime(d.getTime() - 1000 * 60 * 15);
	}

	public AppointmentDAO getAppointmentDAO() {
		return appointmentDAO;
	}

	public void setAppointmentDAO(AppointmentDAO appointmentDAO) {
		this.appointmentDAO = appointmentDAO;
	}

}
