package com.acme.hospital.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="appointment")
public class Appointment {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private Date date;
	
	@Column
	private long doctorId;
	
	@Column
	private long patientId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (doctorId ^ (doctorId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (patientId ^ (patientId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (doctorId != other.doctorId)
			return false;
		if (id != other.id)
			return false;
		if (patientId != other.patientId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", date=" + date.toString() + ", doctorId="
				+ doctorId + ", patientId=" + patientId + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
}
