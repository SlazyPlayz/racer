package bg.softuni.exam_retake_racer.service;

import bg.softuni.exam_retake_racer.model.dto.vehicle.VehicleDTO;

import java.util.Set;

public interface VehicleService {

    void addVehicle(VehicleDTO vehicleDTO);

    Set<VehicleDTO> getVehicleByMake(String make);

    VehicleDTO getVehicleByMakeAndModel(String make, String model);

    Set<VehicleDTO> getAll();

    String getMakeLogoUrl(String make);
}
