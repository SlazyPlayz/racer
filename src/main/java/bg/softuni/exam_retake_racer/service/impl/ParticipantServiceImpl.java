package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.ParticipantAlreadyExistsException;
import bg.softuni.exam_retake_racer.exceptions.ParticipantNotFoundException;
import bg.softuni.exam_retake_racer.exceptions.UserNotFoundException;
import bg.softuni.exam_retake_racer.exceptions.VehicleNotFoundException;
import bg.softuni.exam_retake_racer.model.dto.user.ParticipantDTO;
import bg.softuni.exam_retake_racer.model.entity.user.ParticipantEntity;
import bg.softuni.exam_retake_racer.model.entity.user.UserEntity;
import bg.softuni.exam_retake_racer.model.entity.vehicle.VehicleEntity;
import bg.softuni.exam_retake_racer.repository.ParticipantRepository;
import bg.softuni.exam_retake_racer.repository.UserRepository;
import bg.softuni.exam_retake_racer.repository.VehicleRepository;
import bg.softuni.exam_retake_racer.service.ParticipantService;
import org.springframework.stereotype.Service;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository, UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.participantRepository = participantRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void addParticipant(ParticipantDTO participantDTO) {

        String username = participantDTO.getUsername();
        String vehicleMake = participantDTO.getVehicleMake();
        String vehicleModel = participantDTO.getVehicleModel();

        UserEntity user = getUser(username);
        VehicleEntity vehicle = getVehicle(vehicleMake, vehicleModel);

        if(participantRepository
                .findByUserAndVehicle(getUser(username), getVehicle(vehicleMake, vehicleModel))
                .isPresent()) {
            throw new ParticipantAlreadyExistsException(username, vehicleMake + " " + vehicleModel);
        }

        ParticipantEntity participant = new ParticipantEntity()
                .setUser(user)
                .setVehicle(vehicle);

        participantRepository.save(participant);
    }

    @Override
    public void removeParticipant(ParticipantDTO participantDTO) {

        String username = participantDTO.getUsername();
        String vehicleMake = participantDTO.getVehicleMake();
        String vehicleModel = participantDTO.getVehicleModel();

        UserEntity user = getUser(username);
        VehicleEntity vehicle = getVehicle(vehicleMake, vehicleModel);

        ParticipantEntity participant = new ParticipantEntity()
                .setUser(user)
                .setVehicle(vehicle);

        if (participantRepository.findByUserAndVehicle(user, vehicle).isEmpty()) {
            throw new ParticipantAlreadyExistsException(username, vehicleMake + " " + vehicleModel);
        }

        participantRepository.delete(participant);
    }

    @Override
    public ParticipantDTO getParticipantByUsernameAndVehicle(ParticipantDTO participantDTO) {

        String username = participantDTO.getUsername();
        String vehicleMake = participantDTO.getVehicleMake();
        String vehicleModel = participantDTO.getVehicleModel();

        UserEntity user = getUser(username);
        VehicleEntity vehicle = getVehicle(vehicleMake, vehicleModel);

        return participantRepository
                .findByUserAndVehicle(user, vehicle)
                .map(participantEntity -> new ParticipantDTO()
                        .setUsername(username)
                        .setVehicleMake(vehicleMake)
                        .setVehicleModel(vehicleModel))
                .orElseThrow(() -> new ParticipantNotFoundException(username, vehicleMake + " " + vehicleModel));
    }

    private UserEntity getUser(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    private VehicleEntity getVehicle(String make, String model) {
        return vehicleRepository
                .findByMakeAndModel(make, model)
                .orElseThrow(() -> new VehicleNotFoundException(make, model));
    }
}
