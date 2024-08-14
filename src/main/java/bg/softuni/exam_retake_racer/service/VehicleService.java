package bg.softuni.exam_retake_racer.service;

import bg.softuni.exam_retake_racer.model.dto.vehicle.VehicleDTO;

public interface VehicleService {

    void addVehicle(VehicleDTO vehicleDTO);

    VehicleDTO getVehicleByMakeAndModel(String make, String model);
}
