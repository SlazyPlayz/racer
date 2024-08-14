package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.ImageUploadException;
import bg.softuni.exam_retake_racer.exceptions.OrganizerWithIdentificationNumberNotFoundException;
import bg.softuni.exam_retake_racer.exceptions.OrganizerWithNameNotFoundException;
import bg.softuni.exam_retake_racer.model.dto.race.OrganizerRaceDTO;
import bg.softuni.exam_retake_racer.model.dto.race.organizer.OrganizerAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.organizer.OrganizerDTO;
import bg.softuni.exam_retake_racer.model.entity.race.OrganizerEntity;
import bg.softuni.exam_retake_racer.repository.OrganizerRepository;
import bg.softuni.exam_retake_racer.repository.RaceRepository;
import bg.softuni.exam_retake_racer.service.CloudinaryService;
import bg.softuni.exam_retake_racer.service.OrganizerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrganizerServiceImpl implements OrganizerService {

    private final OrganizerRepository organizerRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    private final RaceRepository raceRepository;

    public OrganizerServiceImpl(OrganizerRepository organizerRepository, ModelMapper modelMapper, CloudinaryService cloudinaryService, RaceRepository raceRepository) {
        this.organizerRepository = organizerRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.raceRepository = raceRepository;
    }

    @Override
    public void addOrganizer(OrganizerAddBindingModel organizerAddBindingModel) {

        OrganizerEntity entity = modelMapper.map(organizerAddBindingModel, OrganizerEntity.class);

        try {
            entity
                    .setImageUrl(cloudinaryService
                            .uploadFile(organizerAddBindingModel.getImage(),
                                    "organizers/" + organizerAddBindingModel.getName()));
        } catch (IOException e) {
            throw new ImageUploadException();
        }

        organizerRepository.save(entity);
    }

    @Override
    public Set<OrganizerRaceDTO> getRacesByOrganizerName(String organizerName) {
        return raceRepository.findByOrganizerName(organizerName)
                .stream()
                .map(raceEntity -> modelMapper
                        .map(raceEntity, OrganizerRaceDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public OrganizerDTO getOrganizerByName(String name) {
        return modelMapper.map(organizerRepository
                .findOrganizerByName(name)
                .orElseThrow(() -> new OrganizerWithNameNotFoundException(name)),
                OrganizerDTO.class);
    }

    @Override
    public Set<OrganizerDTO> getAllOrganizers() {
        return organizerRepository
                .findAll()
                .stream()
                .map(entity -> modelMapper
                        .map(entity, OrganizerDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getAllOrganizerNames() {
        return organizerRepository
                .findAll()
                .stream()
                .map(OrganizerEntity::getName)
                .collect(Collectors.toSet());
    }
}
