package com.vehicle.management.model.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class MaintenanceDetails {

	private Long serviceId;
	private BigDecimal cost;
	private Date date;
	private String remarks;
	private Long vehicleId;
	private Long userId;
}