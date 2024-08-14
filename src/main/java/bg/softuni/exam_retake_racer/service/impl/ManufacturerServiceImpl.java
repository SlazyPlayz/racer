package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.ImageUploadException;
import bg.softuni.exam_retake_racer.exceptions.ManufacturerAlreadyExistsException;
import bg.softuni.exam_retake_racer.exceptions.ManufacturerNotFoundException;
import bg.softuni.exam_retake_racer.model.dto.vehicle.manufacturer.ManufacturerAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.vehicle.manufacturer.ManufacturerDTO;
import bg.softuni.exam_retake_racer.model.entity.vehicle.ManufacturerEntity;
import bg.softuni.exam_retake_racer.repository.ManufacturerRepository;
import bg.softuni.exam_retake_racer.service.CloudinaryService;
import bg.softuni.exam_retake_racer.service.ManufacturerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.manufacturerRepository = manufacturerRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void addManufacturer(ManufacturerAddBindingModel manufacturerAddBindingModel) {

        String name = manufacturerAddBindingModel.getName();

        if(manufacturerRepository.findManufacturerByName(name).isPresent()) {
            throw new ManufacturerAlreadyExistsException(name);
        }

        try {
            cloudinaryService.uploadFile(manufacturerAddBindingModel.getImage(), "/manufacturers/" + name);
        } catch (IOException e) {
            throw new ImageUploadException();
        }

        manufacturerRepository.save(modelMapper.map(manufacturerAddBindingModel, ManufacturerEntity.class));
    }

    @Override
    public void deleteManufacturer(String name) {
        manufacturerRepository.delete(getManufacturerEntityByName(name));
    }

    @Override
    public ManufacturerDTO getManufacturerByName(String name) {
        return modelMapper.map(getManufacturerEntityByName(name), ManufacturerDTO.class);
    }

    private ManufacturerEntity getManufacturerEntityByName(String name) {
        return manufacturerRepository
                .findManufacturerByName(name)
                .orElseThrow(() -> new ManufacturerNotFoundException(name));
    }
}
