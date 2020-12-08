package com.vehicle.management.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name = "VEHICLE_SALE_DETAILS")
public class VehicleSaleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_id")
	private Long saleId;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "vehicle_id")
	private Long vehicleId;

	@Column(name = "ownership")
	private Long ownership;

    @CreationTimestamp
	@Column(name = "date_of_purchase")
	private Date dateOfpurchase;

	@Column(name = "date_of_registration")
	private Date dateOfRegistration;

	@Column(name = "expiry_date")
	private Date expiryDate;
}