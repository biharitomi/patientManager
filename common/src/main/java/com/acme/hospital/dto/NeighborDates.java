package com.acme.hospital.dto;

import java.util.Date;

public class NeighborDates {
	private Date previousDate;
	private Date nextDate;

	public NeighborDates(Date previousDate, Date nextDate) {
		super();
		this.previousDate = previousDate;
		this.nextDate = nextDate;
	}

	public NeighborDates() {
		super();
	}

	public Date getPreviousDate() {
		return previousDate;
	}

	public void setPreviousDate(Date previousDate) {
		this.previousDate = previousDate;
	}

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

}
