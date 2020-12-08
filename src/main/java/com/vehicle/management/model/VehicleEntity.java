package com.vehicle.management.model;

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
@Table(name="VEHICLE_DETAILS")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vehicle_id")
    private Long vehicleId;
    
	@Column(name="vehicle_name")
    private String vehicleName;
    
    @Column(name="manufacturer")
    private String manufacturer;
    
    @Column(name="type")
    private String type;
    
    @Column(name="model")
    private String model;
    
    @Column(name="engine_no")
    private String engineNo;
    
    @Column(name="chassis_no")
    private String chassisNo;
    
    @Column(name="status")
    private String status;
}