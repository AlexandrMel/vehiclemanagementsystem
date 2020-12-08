package com.vehicle.management.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicle.management.exception.OutOfStockException;
import com.vehicle.management.exception.RecordNotFoundException;
import com.vehicle.management.exception.SaleNotMatchExcption;
import com.vehicle.management.model.MaintenanceEntity;
import com.vehicle.management.model.UserEntity;
import com.vehicle.management.model.VehicleEntity;
import com.vehicle.management.model.VehicleSaleEntity;
import com.vehicle.management.model.dto.MaintenanceDetails;
import com.vehicle.management.model.dto.VehicleDetails;
import com.vehicle.management.model.dto.VehicleSaleDetails;
import com.vehicle.management.repository.UserRepository;
import com.vehicle.management.repository.VehicleMaintenanceRepository;
import com.vehicle.management.repository.VehicleRepository;
import com.vehicle.management.repository.VehicleSaleRepository;

@Service
public class VehicleService {

	private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

	@Autowired
	VehicleRepository vehicleRepository;

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	VehicleSaleRepository vehicleSaleRepository;

	@Autowired
	VehicleMaintenanceRepository vehicleMaintenanceRepository;

	public VehicleEntity getVehicle(Long vehicleId) throws RecordNotFoundException {

		log.info("Entered into getVehicle()");

		Optional<VehicleEntity> vehicle = vehicleRepository.findById(vehicleId);

		if (vehicle.isPresent()) {
			return vehicle.get();
		} else {
			log.error("Vehicle record doesn't exist for vehicleId "+vehicleId);
			throw new RecordNotFoundException("Vehicle record doesn't exist for vehicleId "+vehicleId);
		}
	}

	public VehicleEntity createVehicle(VehicleDetails vehicleDetails) {

		log.info("Entered into createVehicle()");
		VehicleEntity vehicleEntity = new VehicleEntity();
		vehicleEntity.setManufacturer(vehicleDetails.getManufacturer());
		vehicleEntity.setModel(vehicleDetails.getModel());
		vehicleEntity.setType(vehicleDetails.getType());
		vehicleEntity.setVehicleName(vehicleDetails.getVehiclename());
		vehicleEntity.setEngineNo(vehicleDetails.getEngineNo());
		vehicleEntity.setChassisNo(vehicleDetails.getChassisNo());
		vehicleEntity.setStatus("AVAILABLE");
		return vehicleRepository.save(vehicleEntity);

	}
	
	public VehicleSaleEntity createSale(VehicleSaleDetails vehicleSaleDetails)
			throws RecordNotFoundException, OutOfStockException {

		log.info("Entered into createSale()");
		
		Optional<UserEntity> user = userRepository.findById(vehicleSaleDetails.getUserId());

		if (!user.isPresent()) {
			log.error("User Not Registered, Please first register user before create a sell ");
			throw new RecordNotFoundException("User Not Registered, Please first register user before create a sell");
		}

		Optional<VehicleEntity> vehicle = vehicleRepository.findById(vehicleSaleDetails.getVehicleId());

		if (vehicle.isPresent()) {
			VehicleEntity vehicleEntity = vehicle.get();
			if (vehicleEntity.getStatus().equalsIgnoreCase("AVAILABLE")) {

				VehicleSaleEntity vehicleSaleEntity = new VehicleSaleEntity();

				vehicleSaleEntity.setVehicleId(vehicleSaleDetails.getVehicleId());
				vehicleSaleEntity.setUserId(vehicleSaleDetails.getUserId());
				vehicleSaleEntity.setOwnership(vehicleSaleDetails.getOwnership());
				vehicleSaleEntity.setExpiryDate(vehicleSaleDetails.getExpiryDate());
				vehicleSaleEntity.setDateOfRegistration(vehicleSaleDetails.getDateOfRegistration());
				vehicleSaleEntity = vehicleSaleRepository.save(vehicleSaleEntity);
				if (vehicleSaleEntity != null) {
					vehicleEntity.setStatus("SALE");
					vehicleRepository.save(vehicleEntity);
				}
				return vehicleSaleEntity;
			} else {
				throw new OutOfStockException("This product already sold out, Please select another vehicle");
			}
		} else {
			log.error("Vehicle Not Found in store, Please select right vehicle for sale");
			throw new RecordNotFoundException("Vehicle Not Found in store, Please select right vehicle for sale");
		}
	}
	
	public MaintenanceEntity createMaintenance(MaintenanceDetails maintenanceDetails)
			throws RecordNotFoundException, SaleNotMatchExcption {

		log.info("Entered into createMaintenance()");

		Optional<VehicleSaleEntity> sale = vehicleSaleRepository.findByVehicleId(maintenanceDetails.getVehicleId());
		if (sale.isPresent()) {
			VehicleSaleEntity vehicleSaleEntity = sale.get();
			if (vehicleSaleEntity.getUserId().equals(maintenanceDetails.getUserId())) {
				MaintenanceEntity maintenanceEntity = new MaintenanceEntity();
				maintenanceEntity.setCost(maintenanceDetails.getCost());
				maintenanceEntity.setDate(maintenanceDetails.getDate());
				maintenanceEntity.setRemarks(maintenanceDetails.getRemarks());
				maintenanceEntity.setUserId(maintenanceDetails.getUserId());
				maintenanceEntity.setVehicleId(maintenanceDetails.getVehicleId());

				return vehicleMaintenanceRepository.save(maintenanceEntity);
			} else {
				log.error("Sale not matched for vehicleId " + maintenanceDetails.getVehicleId() + " with userId "
						+ maintenanceDetails.getUserId());
				throw new SaleNotMatchExcption("Sale not matched for vehicleId " + maintenanceDetails.getVehicleId()
						+ " with userId " + maintenanceDetails.getUserId());
			}
		} else {
			log.error("Sale not found for vehicleId " + maintenanceDetails.getVehicleId());
			throw new RecordNotFoundException("Sale not found for vehicleId : " + maintenanceDetails.getVehicleId());
		}
	}

	public void deleteMaintenanceByServiceId(Long serviceId) throws RecordNotFoundException {

		log.info("Entered into deleteMaintenanceByServiceIdLong()");

		Optional<MaintenanceEntity> maintenance = vehicleMaintenanceRepository.findById(serviceId);

		if (maintenance.isPresent()) {
			vehicleMaintenanceRepository.deleteById(serviceId);
		} else {
			log.error("Maintenance record not found for serviceId : " + serviceId);
			throw new RecordNotFoundException("Maintenance record not found for serviceId : " + serviceId);
		}
	}

	public MaintenanceEntity updateMaintenance(Long serviceId, MaintenanceDetails maintenanceDetails)
			throws RecordNotFoundException, SaleNotMatchExcption {

		log.info("Entered into updateMaintenance()");

		Optional<MaintenanceEntity> maintenance = vehicleMaintenanceRepository.findById(serviceId);

		if (maintenance.isPresent()) {

			MaintenanceEntity existingMaintenance = maintenance.get();
			if (maintenanceDetails.getCost() != null)
				existingMaintenance.setCost(maintenanceDetails.getCost());
			if (maintenanceDetails.getDate() != null)
				existingMaintenance.setDate(maintenanceDetails.getDate());
			if (maintenanceDetails.getRemarks() != null)
				existingMaintenance.setRemarks(maintenanceDetails.getRemarks());

			return vehicleMaintenanceRepository.save(existingMaintenance);
		} else {
			log.error("Maintenance record not found for serviceId : " + serviceId);
			throw new RecordNotFoundException("Maintenance record not found for serviceId : " + serviceId);
		}
	}

	public void transferVehicle(Long vehicleId, Long userId) throws RecordNotFoundException {

		log.info("Entered into transferVehicle()");

		Optional<VehicleSaleEntity> sale = vehicleSaleRepository.findByVehicleId(vehicleId);

		if (sale.isPresent()) {
			VehicleSaleEntity vehicleSaleEntity = sale.get();
			if (userId.equals(vehicleSaleEntity.getUserId())) {
				throw new RecordNotFoundException("Vehicle can not transfer to same User");
			} else {
				
				Optional<UserEntity> user = userRepository.findById(userId);

				if (!user.isPresent()) {
					log.error("User Not Registered, Please first register user before create a sell ");
					throw new RecordNotFoundException("User Not Registered, First register user before initiating transfer");
				}
				
				vehicleSaleEntity.setUserId(userId);
				vehicleSaleEntity.setOwnership(vehicleSaleEntity.getOwnership() + 1);
				VehicleSaleEntity vehicleSaleUpdated = vehicleSaleRepository.save(vehicleSaleEntity);
				
				if (vehicleSaleUpdated != null) {
					
					Optional<List<MaintenanceEntity>> maintenance = vehicleMaintenanceRepository.findByVehicleId(vehicleId);
					
					if (maintenance.isPresent()) {
						List<MaintenanceEntity> listMaintenanceEntity = maintenance.get();
						for(MaintenanceEntity maintenanceEntity : listMaintenanceEntity) {
							maintenanceEntity.setUserId(userId);
							vehicleMaintenanceRepository.save(maintenanceEntity);
						}
					}else {
						log.error("Vehicle Transferred, But Maintenance record not available for vehicle so maintenance record not transferred "+ vehicleId);
						throw new RecordNotFoundException("Vehicle Transferred, But Maintenance record not available for vehicle so maintenance record not transferred "+ vehicleId);
					}
				}
			}
		} else {
			log.error("Vehicle "+vehicleId+" does not exist in sale record");
			throw new RecordNotFoundException("Vehicle does not exist in sale record");
		}
	}
}