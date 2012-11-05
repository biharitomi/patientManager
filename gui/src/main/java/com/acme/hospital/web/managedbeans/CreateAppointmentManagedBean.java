package com.acme.hospital.web.managedbeans;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acme.hospital.domain.Appointment;
import com.acme.hospital.domain.Doctor;
import com.acme.hospital.domain.Patient;
import com.acme.hospital.web.adapter.AppointmentFacadeSpringAdapter;

@ManagedBean(name = "createAppointmentMB")
@RequestScoped
public class CreateAppointmentManagedBean {

	private static Logger logger = LoggerFactory
			.getLogger(CreateAppointmentManagedBean.class);

	@ManagedProperty(value = "#{appointmentFacadeSpringAdapter}")
	private AppointmentFacadeSpringAdapter afsa;

	@ManagedProperty(value = "#{LoginManagedBean}")
	private LoginManagedBean loginManagedBean;

	private Date date;

	private Patient selectedPatient;

	private Appointment selectedAppointment;

	private List<Patient> allPatients;

	private List<Appointment> doctorAppointments;

	private Doctor loggedInDoctor;

	public void createAppointment() {
		boolean result = false;
		if (!(date == null || selectedPatient == null)) {
			String loggedInDoctorName = loginManagedBean.getLoggedInUser();
			loggedInDoctor = afsa.getAppointmentFacade().getDoctorByName(
					loggedInDoctorName);
			result = afsa.getAppointmentFacade().createAppointment(
					loggedInDoctor, selectedPatient, date);
		}
		generateMessage(result);
	}

	public void generateMessage(boolean result) {
		if (result) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"The appointment creation was successful!  Patient: "
									+ selectedPatient.getName() + "|| Date: "
									+ date));
			logger.info("The appointment creation was successful! For Doctor: "
					+ loggedInDoctor.getName() + "|| Patient: "
					+ selectedPatient.getName() + "|| Date: " + date);
		} else {
			if (selectedPatient == null) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Error",
										"The appointment creation was unsuccessful. No patient selected"));
				logger.info("The appointment creation was unsuccessful. No patient selected");
			} else if (date == null) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR,
										"Error",
										"The appointment creation was unsuccessful. No date selected"));
				logger.info("The appointment creation was unsuccessful. No date selected");
			}
		}
	}

	public List<Appointment> getDoctorAppointments() {
		Doctor d = afsa.getAppointmentFacade().getDoctorByName(
				loginManagedBean.getLoggedInUser());
		doctorAppointments = (List<Appointment>) afsa.getAppointmentFacade()
				.getDoctorAllAppointments(d);
		return doctorAppointments;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Patient> getAllPatients() {
		this.allPatients = afsa.getAllPatients();
		return this.allPatients;
	}

	public Patient getSelectedPatient() {
		return selectedPatient;
	}

	public void setSelectedPatient(Patient selectedPatient) {
		this.selectedPatient = selectedPatient;
	}

	public AppointmentFacadeSpringAdapter getAfsa() {
		return afsa;
	}

	public void setAfsa(AppointmentFacadeSpringAdapter afsa) {
		this.afsa = afsa;
	}

	public LoginManagedBean getLoginManagedBean() {
		return loginManagedBean;
	}

	public void setLoginManagedBean(LoginManagedBean loginManagedBean) {
		this.loginManagedBean = loginManagedBean;
	}

	public Appointment getSelectedAppointment() {
		return selectedAppointment;
	}

	public void setSelectedAppointment(Appointment selectedAppointment) {
		this.selectedAppointment = selectedAppointment;
	}

}
