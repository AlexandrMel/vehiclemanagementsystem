package com.vehicle.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vehicle.management.model.VehicleEntity;
 
@Repository
public interface VehicleRepository
        extends JpaRepository<VehicleEntity, Long> {
 
}
