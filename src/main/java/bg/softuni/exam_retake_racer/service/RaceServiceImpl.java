package bg.softuni.exam_retake_racer.service;

import bg.softuni.exam_retake_racer.exceptions.RaceAlreadyExistsException;
import bg.softuni.exam_retake_racer.exceptions.RaceNotFoundException;
import bg.softuni.exam_retake_racer.exceptions.UserNotFoundException;
import bg.softuni.exam_retake_racer.model.dto.race.RaceAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.RaceDTO;
import bg.softuni.exam_retake_racer.model.entity.race.RaceEntity;
import bg.softuni.exam_retake_racer.model.entity.user.UserEntity;
import bg.softuni.exam_retake_racer.repository.RaceRepository;
import bg.softuni.exam_retake_racer.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    public RaceServiceImpl(RaceRepository raceRepository, ModelMapper modelMapper, UserService userService, UserRepository userRepository) {
        this.raceRepository = raceRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void addRace(RaceAddBindingModel raceAddBindingModel) {
        raceRepository.findByName(raceAddBindingModel.getName())
                .ifPresent(raceEntity -> {
                    throw new RaceAlreadyExistsException(raceAddBindingModel.getName());
                });

        // TODO: Add mapping in configuration
        raceRepository.save(modelMapper.map(raceAddBindingModel, RaceEntity.class));
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
