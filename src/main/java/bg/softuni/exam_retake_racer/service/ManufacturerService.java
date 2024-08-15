package bg.softuni.exam_retake_racer.service;

import bg.softuni.exam_retake_racer.model.dto.vehicle.manufacturer.ManufacturerAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.vehicle.manufacturer.ManufacturerDTO;

import java.util.Set;

public interface ManufacturerService {

    Set<ManufacturerDTO> getAllManufacturers();

    void addManufacturer(ManufacturerAddBindingModel manufacturerAddBindingModel);

    void deleteManufacturer(String name);

    ManufacturerDTO getManufacturerByName(String name);

    ManufacturerDTO getManufacturerBySearchName(String name);

    Set<String> getPhotoIds();
}
