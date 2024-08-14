package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.*;
import bg.softuni.exam_retake_racer.model.dto.race.RaceAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.RaceDTO;
import bg.softuni.exam_retake_racer.model.entity.race.RaceEntity;
import bg.softuni.exam_retake_racer.model.entity.user.UserEntity;
import bg.softuni.exam_retake_racer.repository.OrganizerRepository;
import bg.softuni.exam_retake_racer.repository.RaceRepository;
import bg.softuni.exam_retake_racer.repository.UserRepository;
import bg.softuni.exam_retake_racer.service.CloudinaryService;
import bg.softuni.exam_retake_racer.service.OrganizerService;
import bg.softuni.exam_retake_racer.service.RaceService;
import bg.softuni.exam_retake_racer.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

@Service
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final OrganizerRepository organizerRepository;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;

    public RaceServiceImpl(RaceRepository raceRepository, ModelMapper modelMapper, UserService userService, OrganizerRepository organizerRepository, UserRepository userRepository, CloudinaryService cloudinaryService) {
        this.raceRepository = raceRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.organizerRepository = organizerRepository;
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void addRace(RaceAddBindingModel raceAddBindingModel) {
        raceRepository.findByName(raceAddBindingModel.getName())
                .ifPresent(raceEntity -> {
                    throw new RaceAlreadyExistsException(raceAddBindingModel.getName());
                });

        RaceEntity raceEntity = modelMapper.map(raceAddBindingModel, RaceEntity.class);

        try {
            String imageUrl = cloudinaryService.uploadFile(raceAddBindingModel.getImage(), "races/" + raceAddBindingModel.getName());
            raceEntity.setImageUrl(imageUrl);
        } catch (IOException e) {
            throw new ImageUploadException();
        }

        raceEntity.setOrganizer(organizerRepository.findOrganizerByName(raceAddBindingModel.getOrganizer())
                .orElseThrow(() -> new OrganizerWithNameNotFoundException(raceAddBindingModel.getOrganizer())));

        if (raceAddBindingModel.getWillParticipate()) {
            raceEntity.setParticipants(Set.of(userRepository.findByUsername(userService.getUser().getUsername())
                    .orElseThrow(() -> new UserNotFoundException(userService.getUser().getUsername()))));
        }

        raceRepository.save(raceEntity);
    }

    @Override
    public RaceDTO getRaceByName(String name) {
        return raceRepository.findByName(name)
                .map(raceEntity -> modelMapper.map(raceEntity, RaceDTO.class))
                .orElseThrow(() -> new RaceNotFoundException(name));
    }

    @Override
    public void participateInRace(String raceName) {
        RaceEntity raceEntity = raceRepository.findByName(raceName)
                .orElseThrow(() -> new RaceNotFoundException(raceName));

        String username = userService.getUser().getUsername();
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        Set<UserEntity> participants = raceEntity.getParticipants();
        participants.add(userEntity);
        raceEntity.setParticipants(participants);
    }
}
