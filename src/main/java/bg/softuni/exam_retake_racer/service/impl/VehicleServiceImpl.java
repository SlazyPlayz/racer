package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.ManufacturerNotFoundException;
import bg.softuni.exam_retake_racer.exceptions.VehicleAlreadyExists;
import bg.softuni.exam_retake_racer.exceptions.VehicleNotFoundException;
import bg.softuni.exam_retake_racer.model.dto.vehicle.VehicleDTO;
import bg.softuni.exam_retake_racer.model.entity.vehicle.ManufacturerEntity;
import bg.softuni.exam_retake_racer.model.entity.vehicle.VehicleEntity;
import bg.softuni.exam_retake_racer.repository.ManufacturerRepository;
import bg.softuni.exam_retake_racer.repository.VehicleRepository;
import bg.softuni.exam_retake_racer.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelMapper) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addVehicle(VehicleDTO vehicleDTO) {

        String make = vehicleDTO.getMake();
        String model = vehicleDTO.getModel();

        if(vehicleRepository.findByMakeAndModel(make, model).isPresent()) {
            throw new VehicleAlreadyExists(make, model);
        }

        vehicleRepository.save(modelMapper.map(vehicleDTO, VehicleEntity.class));
    }

    @Override
    public VehicleDTO getVehicleByMakeAndModel(String make, String model) {
        return vehicleRepository
                .findByMakeAndModel(make, model)
                .map(vehicleEntity -> modelMapper.map(vehicleEntity, VehicleDTO.class))
                .orElseThrow(() -> new VehicleNotFoundException(make, model));
    }
}
