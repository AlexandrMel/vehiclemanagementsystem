package com.vehicle.management.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class VehicleSaleDetails {

	private Long userId;
	private Long vehicleId;
	private Long ownership;
	private Date dateOfRegistration;
	private Date expiryDate;
}