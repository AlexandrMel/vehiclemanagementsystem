package com.vehicle.management.util;

import javax.validation.ValidationException;

import org.springframework.util.StringUtils;

import com.vehicle.management.model.dto.MaintenanceDetails;
import com.vehicle.management.model.dto.UserDetails;
import com.vehicle.management.model.dto.VehicleDetails;
import com.vehicle.management.model.dto.VehicleSaleDetails;

public class Validation {
	
	public static void validateCreateUserRequest(UserDetails userDetails) {
		 if (StringUtils.isEmpty(userDetails.getName()) || userDetails.getName()==null)
			throw new ValidationException("User Name must be supplied.");
		 if (StringUtils.isEmpty(userDetails.getAddress()) || userDetails.getAddress()==null)
			throw new ValidationException("Address must be supplied.");
		 if (StringUtils.isEmpty(userDetails.getPhone()) || userDetails.getPhone()==null)
			throw new ValidationException("Phone must be supplied.");
	}
	
	public static void validateCreateVehicleRequest(VehicleDetails vehicleDetails) {
		 if (StringUtils.isEmpty(vehicleDetails.getVehiclename()) || vehicleDetails.getVehiclename()==null)
			throw new ValidationException("Vehicle Name must be supplied.");
		 if (StringUtils.isEmpty(vehicleDetails.getManufacturer()) || vehicleDetails.getManufacturer()==null)
			throw new ValidationException("Manufacturer Name must be supplied.");
		 if (StringUtils.isEmpty(vehicleDetails.getModel()) || vehicleDetails.getModel()==null)
			throw new ValidationException("Model Name must be supplied.");
		 if (StringUtils.isEmpty(vehicleDetails.getType()) || vehicleDetails.getType()==null)
				throw new ValidationException("Type must be supplied.");
		 if (StringUtils.isEmpty(vehicleDetails.getEngineNo()) || vehicleDetails.getEngineNo()==null)
				throw new ValidationException("Engine No must be supplied.");
		 if (StringUtils.isEmpty(vehicleDetails.getChassisNo()) || vehicleDetails.getChassisNo()==null)
				throw new ValidationException("Chassis No must be supplied.");
	}
	
	public static void validateSaleRequest(VehicleSaleDetails vehicleSaleDetails) {
		 if (StringUtils.isEmpty(vehicleSaleDetails.getUserId()) || vehicleSaleDetails.getUserId()==null)
			throw new ValidationException("User Id must be supplied.");
		 if (StringUtils.isEmpty(vehicleSaleDetails.getVehicleId()) || vehicleSaleDetails.getVehicleId()==null)
			throw new ValidationException("Vehicle Id must be supplied.");
		 if (StringUtils.isEmpty(vehicleSaleDetails.getDateOfRegistration()) || vehicleSaleDetails.getDateOfRegistration()==null)
			throw new ValidationException("Date of registration must be supplied.");
		 if (StringUtils.isEmpty(vehicleSaleDetails.getExpiryDate()) || vehicleSaleDetails.getExpiryDate()==null)
				throw new ValidationException("Date of Expiry must be supplied.");
	}
	
	public static void validateMaintenanceRequest(MaintenanceDetails maintenanceDetails) {
		 if (StringUtils.isEmpty(maintenanceDetails.getUserId()) || maintenanceDetails.getUserId()==null)
			throw new ValidationException("User Id must be supplied.");
		 if (StringUtils.isEmpty(maintenanceDetails.getVehicleId()) || maintenanceDetails.getVehicleId()==null)
			throw new ValidationException("Vehicle Id must be supplied.");
		 if (StringUtils.isEmpty(maintenanceDetails.getCost()) || maintenanceDetails.getCost()==null)
			throw new ValidationException("Date of registration must be supplied.");
		 if (StringUtils.isEmpty(maintenanceDetails.getDate()) || maintenanceDetails.getDate()==null)
				throw new ValidationException("Date of Expiry must be supplied.");
	}
}
