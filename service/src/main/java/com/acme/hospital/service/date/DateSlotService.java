package com.acme.hospital.service.date;

import java.util.Date;

import com.acme.hospital.domain.Doctor;
import com.acme.hospital.dto.NeighborDates;

public interface DateSlotService {
	
	public boolean isSlotFree(Doctor doctor, Date date);

	public NeighborDates findFreeNeighborSlot(Doctor doctor, Date date);

	public Date findNextFreeNeighbourSlot(Doctor doctor, Date date);
}
