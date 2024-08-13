package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.OrganizerWithIdentificationNumberNotFoundException;
import bg.softuni.exam_retake_racer.model.dto.OrganizerDTO;
import bg.softuni.exam_retake_racer.model.entity.race.OrganizerEntity;
import bg.softuni.exam_retake_racer.repository.OrganizerRepository;
import bg.softuni.exam_retake_racer.service.OrganizerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrganizerServiceImpl implements OrganizerService {
    private final OrganizerRepository organizerRepository;
    private final ModelMapper modelMapper;

    public OrganizerServiceImpl(OrganizerRepository organizerRepository, ModelMapper modelMapper) {
        this.organizerRepository = organizerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addOrganizer(OrganizerDTO organizerDTO) {
        organizerRepository.save(modelMapper.map(organizerDTO, OrganizerEntity.class));
    }

    @Override
    public OrganizerDTO getOrganizerByName(String name) {
        return modelMapper.map(organizerRepository
                .findOrganizerEntityByName(name)
                .orElseThrow(() -> new OrganizerWithIdentificationNumberNotFoundException(name)),
                OrganizerDTO.class);
    }

    @Override
    public OrganizerDTO getOrganizerByIdentificationNumber(String identificationNumber) {
        OrganizerEntity entity = organizerRepository
                .findOrganizerEntityByIdentificationNumber(identificationNumber)
                .orElseThrow(() -> new OrganizerWithIdentificationNumberNotFoundException(identificationNumber));

        return modelMapper.map(entity, OrganizerDTO.class);
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
}
