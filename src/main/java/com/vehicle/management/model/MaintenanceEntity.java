package com.vehicle.management.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name = "MAINTENANCE_DETAILS")
public class MaintenanceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="service_id")
	private Long serviceId;
	
    @Column(name="cost")
	private BigDecimal cost;

    @Column(name="date")
	private Date date;

    @Column(name="remarks")
	private String remarks;
    
    @Column(name = "user_id")
	private Long userId;

	@Column(name = "vehicle_id")
	private Long vehicleId;
}