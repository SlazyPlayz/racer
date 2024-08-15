package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.*;
import bg.softuni.exam_retake_racer.model.dto.race.RaceAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.RaceDTO;
import bg.softuni.exam_retake_racer.model.dto.user.ParticipantDTO;
import bg.softuni.exam_retake_racer.model.dto.user.UserDisplayDTO;
import bg.softuni.exam_retake_racer.model.dto.vehicle.VehicleDTO;
import bg.softuni.exam_retake_racer.model.entity.race.RaceEntity;
import bg.softuni.exam_retake_racer.model.entity.race.TrackEntity;
import bg.softuni.exam_retake_racer.model.entity.user.ParticipantEntity;
import bg.softuni.exam_retake_racer.model.entity.user.UserEntity;
import bg.softuni.exam_retake_racer.model.entity.vehicle.VehicleEntity;
import bg.softuni.exam_retake_racer.repository.*;
import bg.softuni.exam_retake_racer.service.RaceService;
import bg.softuni.exam_retake_racer.service.UserService;
import bg.softuni.exam_retake_racer.util.AuthenticationFacade;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final OrganizerRepository organizerRepository;
    private final UserRepository userRepository;
    private final TrackRepository trackRepository;
    private final VehicleRepository vehicleRepository;
    private final ParticipantRepository participantRepository;
    private final AuthenticationFacade authenticationFacade;

    public RaceServiceImpl(RaceRepository raceRepository, ModelMapper modelMapper, UserService userService, OrganizerRepository organizerRepository, UserRepository userRepository, TrackRepository trackRepository, VehicleRepository vehicleRepository, ParticipantRepository participantRepository, AuthenticationFacade authenticationFacade) {
        this.raceRepository = raceRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.organizerRepository = organizerRepository;
        this.userRepository = userRepository;
        this.trackRepository = trackRepository;
        this.vehicleRepository = vehicleRepository;
        this.participantRepository = participantRepository;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public void addRace(RaceAddBindingModel raceAddBindingModel) {

        String searchName = toSearchName(raceAddBindingModel.getName());

        raceRepository.findBySearchName(searchName)
                .ifPresent(raceEntity -> {
                    throw new RaceAlreadyExistsException(raceAddBindingModel.getName());
                });

        RaceEntity raceEntity = modelMapper.map(raceAddBindingModel, RaceEntity.class);

        raceEntity.setSearchName(searchName);

        String trackName = raceAddBindingModel.getTrack();

        TrackEntity trackEntity = trackRepository.findTrackBySearchName(toSearchName(trackName))
                .orElseThrow(() -> new TrackNotFoundException(trackName));

        raceEntity.setTrack(trackEntity);

        raceEntity.setImageUrl(trackEntity.getImageUrl());

        raceEntity.setOrganizer(organizerRepository.findOrganizerByName(raceAddBindingModel.getOrganizer())
                .orElseThrow(() -> new OrganizerWithNameNotFoundException(raceAddBindingModel.getOrganizer())));

        raceRepository.save(raceEntity);
    }

    @Override
    public RaceDTO getRaceByName(String name) {
        return raceRepository.findBySearchName(toSearchName(name))
                .map(this::toDto)
                .orElseThrow(() -> new RaceNotFoundException(name));
    }

    @Override
    public void participateInRace(String raceName, VehicleDTO vehicleDTO) {
        RaceEntity raceEntity = raceRepository.findBySearchName(toSearchName(raceName))
                .orElseThrow(() -> new RaceNotFoundException(raceName));

        String username = userService.getUser().getUsername();
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        String vehicleMake = vehicleDTO.getMake();
        String vehicleModel = vehicleDTO.getModel();
        VehicleEntity vehicleEntity = vehicleRepository
                .findByMakeAndModel(vehicleMake, vehicleModel)
                .orElseThrow(() -> new VehicleNotFoundException(vehicleMake, vehicleModel));

        Set<ParticipantEntity> participants = raceEntity.getParticipants();
        participants
                .add(participantRepository.findByUserAndVehicle(userEntity, vehicleEntity)
                        .orElseThrow(() -> new ParticipantNotFoundException(username, vehicleMake + " " + vehicleModel)));
        raceEntity.setParticipants(participants);
    }

    @Override
    public Set<RaceDTO> getAllRaces() {
        return raceRepository
                .findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ParticipantDTO> getParticipants(String name) {
        return raceRepository
                .findBySearchName(name)
                .stream()
                .map(RaceEntity::getParticipants)
                .map(entity -> modelMapper.map(entity, ParticipantDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RaceDTO> getUserRaces() {
        Set<ParticipantEntity> participants = participantRepository.findByUser(getUserEntity());
        return raceRepository
                .findAllByParticipantsContaining(participants)
                .stream()
                .map(entity -> modelMapper.map(entity, RaceDTO.class))
                .collect(Collectors.toSet());
    }

    private String toSearchName(String name) {
        return name.replace(" ", "-").toLowerCase();
    }

    private RaceDTO toDto(RaceEntity raceEntity) {
        RaceDTO raceDTO = modelMapper.map(raceEntity, RaceDTO.class);
        Set<UserDisplayDTO> participants = raceEntity
                .getParticipants()
                .stream()
                .map(participant -> modelMapper.map(participant, UserDisplayDTO.class))
                .collect(Collectors.toSet());
        raceDTO.setParticipants(participants);
        return raceDTO;
    }

    private UserEntity getUserEntity() {
        String username = authenticationFacade.getAuthentication().getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }
}
