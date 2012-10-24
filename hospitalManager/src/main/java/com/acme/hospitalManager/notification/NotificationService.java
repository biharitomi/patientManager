package com.acme.hospitalManager.notification;

import java.util.Date;

import com.acme.hospital.domain.Patient;

public interface NotificationService {
	public void sendNotification(Patient patient, Date modifiedDate, Date newDate);
}
