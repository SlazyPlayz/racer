package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.*;
import bg.softuni.exam_retake_racer.model.dto.race.RaceAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.RaceDTO;
import bg.softuni.exam_retake_racer.model.entity.race.RaceEntity;
import bg.softuni.exam_retake_racer.model.entity.race.TrackEntity;
import bg.softuni.exam_retake_racer.model.entity.user.UserEntity;
import bg.softuni.exam_retake_racer.repository.OrganizerRepository;
import bg.softuni.exam_retake_racer.repository.RaceRepository;
import bg.softuni.exam_retake_racer.repository.TrackRepository;
import bg.softuni.exam_retake_racer.repository.UserRepository;
import bg.softuni.exam_retake_racer.service.RaceService;
import bg.softuni.exam_retake_racer.service.UserService;
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

    public RaceServiceImpl(RaceRepository raceRepository, ModelMapper modelMapper, UserService userService, OrganizerRepository organizerRepository, UserRepository userRepository, TrackRepository trackRepository) {
        this.raceRepository = raceRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.organizerRepository = organizerRepository;
        this.userRepository = userRepository;
        this.trackRepository = trackRepository;
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

        TrackEntity trackEntity = trackRepository.findTrackBySearchName(trackName)
                .orElseThrow(() -> new TrackNotFoundException(trackName));

        raceEntity.setTrack(trackEntity);

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
        return raceRepository.findBySearchName(toSearchName(name))
                .map(raceEntity -> modelMapper.map(raceEntity, RaceDTO.class))
                .orElseThrow(() -> new RaceNotFoundException(name));
    }

    @Override
    public void participateInRace(String raceName) {
        RaceEntity raceEntity = raceRepository.findBySearchName(toSearchName(raceName))
                .orElseThrow(() -> new RaceNotFoundException(raceName));

        String username = userService.getUser().getUsername();
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        Set<UserEntity> participants = raceEntity.getParticipants();
        participants.add(userEntity);
        raceEntity.setParticipants(participants);
    }

    @Override
    public Set<RaceDTO> getAllRaces() {
        return raceRepository
                .findAll()
                .stream()
                .map(race -> modelMapper.map(race, RaceDTO.class))
                .collect(Collectors.toSet());
    }

    private String toSearchName(String name) {
        return name.replace(" ", "").toLowerCase();
    }
}
