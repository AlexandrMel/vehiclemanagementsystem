package com.vehicle.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vehicle.management.model.VehicleSaleEntity;
 
@Repository
public interface VehicleSaleRepository
        extends JpaRepository<VehicleSaleEntity, Long> {
	
	Optional<VehicleSaleEntity> findByVehicleId(Long vehicleId);
 
}
