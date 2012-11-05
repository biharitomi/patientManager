package com.acme.hospital.exception;

@SuppressWarnings("serial")
public class ReservedAppointmentException extends RuntimeException {
	
	public ReservedAppointmentException(){
		super();
	}
	
	public ReservedAppointmentException(String msg) {
		super(msg);
	}
}
