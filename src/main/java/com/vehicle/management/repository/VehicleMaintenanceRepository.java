package com.vehicle.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vehicle.management.model.MaintenanceEntity;
 
@Repository
public interface VehicleMaintenanceRepository
        extends JpaRepository<MaintenanceEntity, Long> {
	Optional<List<MaintenanceEntity>> findByVehicleId(Long vehicleId);
}
