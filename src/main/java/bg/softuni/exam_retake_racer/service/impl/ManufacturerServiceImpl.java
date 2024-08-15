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
import java.util.Set;
import java.util.stream.Collectors;

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
    public Set<ManufacturerDTO> getAllManufacturers() {
        return manufacturerRepository
                .findAll()
                .stream()
                .map(manufacturerEntity -> modelMapper.map(manufacturerEntity, ManufacturerDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public void addManufacturer(ManufacturerAddBindingModel manufacturerAddBindingModel) {

        String name = manufacturerAddBindingModel.getName();

        if(manufacturerRepository.findManufacturerByName(name).isPresent()) {
            throw new ManufacturerAlreadyExistsException(name);
        }

        ManufacturerEntity manufacturer = modelMapper.map(manufacturerAddBindingModel, ManufacturerEntity.class);

        manufacturer.setSearchName(toSearchName(name));

        try {
            String imageUrl = cloudinaryService.uploadFile(manufacturerAddBindingModel.getImage(), "/manufacturers/" + name);
            manufacturer.setImageUrl(imageUrl);
        } catch (IOException e) {
            throw new ImageUploadException();
        }

        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void deleteManufacturer(String name) {
        manufacturerRepository.delete(getManufacturerEntityByName(name));
    }

    @Override
    public ManufacturerDTO getManufacturerByName(String name) {
        return modelMapper.map(getManufacturerEntityByName(name), ManufacturerDTO.class);
    }

    @Override
    public ManufacturerDTO getManufacturerBySearchName(String name) {
        return manufacturerRepository
                .findManufacturerBySearchName(name)
                .map(manufacturerEntity -> modelMapper.map(manufacturerEntity, ManufacturerDTO.class))
                .orElseThrow(() -> new ManufacturerNotFoundException(name));
    }

    @Override
    public Set<String> getPhotoIds() {
        return manufacturerRepository
                .findAll()
                .stream()
                .map(ManufacturerEntity::getImageUrl)
                .collect(Collectors.toSet());
    }

    private ManufacturerEntity getManufacturerEntityByName(String name) {
        return manufacturerRepository
                .findManufacturerByName(name)
                .orElseThrow(() -> new ManufacturerNotFoundException(name));
    }

    private String toSearchName(String name) {
        return name.replace(" ", "-").toLowerCase();
    }
}
