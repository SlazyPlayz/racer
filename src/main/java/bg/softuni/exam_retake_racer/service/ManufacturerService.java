package bg.softuni.exam_retake_racer.service;

import bg.softuni.exam_retake_racer.model.dto.vehicle.manufacturer.ManufacturerAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.vehicle.manufacturer.ManufacturerDTO;

public interface ManufacturerService {

    void addManufacturer(ManufacturerAddBindingModel manufacturerAddBindingModel);

    void deleteManufacturer(String name);

    ManufacturerDTO getManufacturerByName(String name);
}
