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

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;
    private final ManufacturerRepository manufacturerRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelMapper, ManufacturerRepository manufacturerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public void addVehicle(VehicleDTO vehicleDTO) {

        String make = vehicleDTO.getMake();
        String model = vehicleDTO.getModel();


        if(vehicleRepository.findByMakeAndModel(make, model).isPresent()) {
            throw new VehicleAlreadyExists(make, model);
        }

        VehicleEntity entity = modelMapper.map(vehicleDTO, VehicleEntity.class);
        entity.setSearchName(toSearchName(model));

        vehicleRepository.save(entity);
    }

    @Override
    public Set<VehicleDTO> getVehicleByMake(String make) {
        return vehicleRepository
                .findAllByMake(make)
                .stream()
                .map(vehicleEntity -> modelMapper.map(vehicleEntity, VehicleDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public VehicleDTO getVehicleBySearchName(String name) {
        return vehicleRepository
                .findVehicleBySearchName(name)
                .map(vehicleEntity -> modelMapper.map(vehicleEntity, VehicleDTO.class))
                .orElseThrow(() -> new VehicleNotFoundException(name));

    }

    @Override
    public VehicleDTO getVehicleByMakeAndModel(String make, String model) {
        return vehicleRepository
                .findByMakeAndModel(make, model)
                .map(vehicleEntity -> modelMapper.map(vehicleEntity, VehicleDTO.class))
                .orElseThrow(() -> new VehicleNotFoundException(make, model));
    }

    @Override
    public Set<VehicleDTO> getAll() {
        return vehicleRepository
                .findAll()
                .stream()
                .map(vehicleEntity -> modelMapper.map(vehicleEntity, VehicleDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public String getMakeLogoUrl(String make) {
        return manufacturerRepository
                .findManufacturerByName(make)
                .map(ManufacturerEntity::getImageUrl)
                .orElseThrow(() -> new ManufacturerNotFoundException(make));
    }


    private String toSearchName(String model) {
        return model.replace(" ", "-").toLowerCase();
    }
}
