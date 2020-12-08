package com.vehicle.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.management.exception.OutOfStockException;
import com.vehicle.management.exception.RecordNotFoundException;
import com.vehicle.management.exception.SaleNotMatchExcption;
import com.vehicle.management.model.MaintenanceEntity;
import com.vehicle.management.model.VehicleEntity;
import com.vehicle.management.model.VehicleSaleEntity;
import com.vehicle.management.model.dto.MaintenanceDetails;
import com.vehicle.management.model.dto.VehicleDetails;
import com.vehicle.management.model.dto.VehicleSaleDetails;
import com.vehicle.management.service.VehicleService;
import com.vehicle.management.util.Validation;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

	@Autowired
	VehicleService vehicleService;

	private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

	@Operation(operationId = "Find Vehicle", summary = "Find Vehicle using vehicle Id")
	@GetMapping("/{vehicleId}")
	public ResponseEntity<VehicleEntity> getUser(@PathVariable("vehicleId") Long vehicleId)
			throws RecordNotFoundException {
		log.info("Entered into getUser()");
		VehicleEntity entity = vehicleService.getVehicle(vehicleId);
		return new ResponseEntity<VehicleEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createvehicle")
	@Operation(operationId = "Create Vehicle", summary = "Create Vehicle in Vehicle Management System")
	public VehicleEntity createVehicle(@RequestBody VehicleDetails vehicleDetails) {
		Validation.validateCreateVehicleRequest(vehicleDetails);
		log.info("Entered into createVehicle() ");
		return vehicleService.createVehicle(vehicleDetails);
	}

	@PostMapping("/vehiclesale")
	@Operation(operationId = "Adding Vehicles to users", summary = "Adding Vehicles to users in Vehicle Management System")
	public VehicleSaleEntity createSale(@RequestBody VehicleSaleDetails vehicleSaleDetails)
			throws RecordNotFoundException, OutOfStockException {
		Validation.validateSaleRequest(vehicleSaleDetails);
		log.info("Entered into createSale()");
		return vehicleService.createSale(vehicleSaleDetails);
	}

	@PostMapping("/maintenance")
	@Operation(operationId = "Create a maintenance record of vehicle", summary = "Create a maintenance record of vehicle in Vehicle Management System")
	public MaintenanceEntity maintenance(@RequestBody MaintenanceDetails maintenanceDetails)
			throws RecordNotFoundException, SaleNotMatchExcption {
		Validation.validateMaintenanceRequest(maintenanceDetails);
		log.info("Entered into maintenance()");
		return vehicleService.createMaintenance(maintenanceDetails);
	}

	@PutMapping("/maintenance/{serviceId}")
	@Operation(operationId = "Update maintenance record of vehicle", summary = "Update maintenance record of vehicle in Vehicle Management System")
	public MaintenanceEntity updateServicing(@PathVariable("serviceId") Long serviceId,
			@RequestBody MaintenanceDetails maintenanceDetails) throws RecordNotFoundException, SaleNotMatchExcption {
		log.info("Entered into updateServicing()");
		return vehicleService.updateMaintenance(serviceId, maintenanceDetails);
	}

	@DeleteMapping("/maintenance/{serviceId}")
	@Operation(operationId = "Delete maintenance record of vehicle", summary = "Delete maintenance record of vehicle in Vehicle Management System")
	public void deleteMaintenanceByServiceId(@PathVariable("serviceId") Long serviceId)
			throws RecordNotFoundException, SaleNotMatchExcption {
		log.info("Entered into deleteMaintenanceByServiceId()");
		vehicleService.deleteMaintenanceByServiceId(serviceId);
	}

	@PutMapping("/transfer/{vehicleId}/{userId}")
	@Operation(operationId = "Transfering ownership of vehicle to another user", summary = "Transfering ownership of vehicle to another user in Vehicle Management System")
	public ResponseEntity<String> transferVehicle(@PathVariable("vehicleId") Long vehicleId, @PathVariable("userId") Long userId)
			throws RecordNotFoundException {
		log.info("Entered into updateServicing()");
		vehicleService.transferVehicle(vehicleId, userId);
		return ResponseEntity.ok().body("Vehicle Transferred Succesfully with maintenance Records");
	}
}