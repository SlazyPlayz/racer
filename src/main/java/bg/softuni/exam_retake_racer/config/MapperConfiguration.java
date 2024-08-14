package bg.softuni.exam_retake_racer.config;

import bg.softuni.exam_retake_racer.model.dto.race.DisplayRaceDTO;
import bg.softuni.exam_retake_racer.model.dto.race.RaceAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.RaceDTO;
import bg.softuni.exam_retake_racer.model.dto.race.organizer.OrganizerAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.organizer.OrganizerDTO;
import bg.softuni.exam_retake_racer.model.dto.race.track.TrackAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.track.TrackDTO;
import bg.softuni.exam_retake_racer.model.dto.user.UserDTO;
import bg.softuni.exam_retake_racer.model.dto.user.UserRegisterBindingModel;
import bg.softuni.exam_retake_racer.model.dto.vehicle.VehicleDTO;
import bg.softuni.exam_retake_racer.model.dto.vehicle.manufacturer.ManufacturerAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.vehicle.manufacturer.ManufacturerDTO;
import bg.softuni.exam_retake_racer.model.entity.race.OrganizerEntity;
import bg.softuni.exam_retake_racer.model.entity.race.RaceEntity;
import bg.softuni.exam_retake_racer.model.entity.race.TrackEntity;
import bg.softuni.exam_retake_racer.model.entity.user.UserEntity;
import bg.softuni.exam_retake_racer.model.entity.vehicle.ManufacturerEntity;
import bg.softuni.exam_retake_racer.model.entity.vehicle.VehicleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // User mappings

        modelMapper.typeMap(UserEntity.class, UserDTO.class)
                .addMappings(mapper -> mapper.map(
                        UserEntity::getFirstName,
                        UserDTO::setFirstName
                ))
                .addMappings(mapper -> mapper.map(
                        UserEntity::getLastName,
                        UserDTO::setLastName
                ))
                .addMappings(mapper -> mapper.map(
                        UserEntity::getUsername,
                        UserDTO::setUsername
                ))
                .addMappings(mapper -> mapper.map(
                        UserEntity::getEmail,
                        UserDTO::setEmail
                ))
                .addMappings(mapper -> mapper.map(
                        UserEntity::getBio,
                        UserDTO::setBio
                ))
                .addMappings(mapper -> mapper.map(
                        UserEntity::getImageUrl,
                        UserDTO::setImageUrl
                ));

        modelMapper.typeMap(UserDTO.class, UserEntity.class)
                .addMappings(mapper -> mapper.map(
                        UserDTO::getFirstName,
                        UserEntity::setFirstName
                ))
                .addMappings(mapper -> mapper.map(
                        UserDTO::getLastName,
                        UserEntity::setLastName
                ))
                .addMappings(mapper -> mapper.map(
                        UserDTO::getUsername,
                        UserEntity::setUsername
                ))
                .addMappings(mapper -> mapper.map(
                        UserDTO::getEmail,
                        UserEntity::setEmail
                ))
                .addMappings(mapper -> mapper.map(
                        UserDTO::getBio,
                        UserEntity::setBio
                ))
                .addMappings(mapper -> mapper.map(
                        UserDTO::getImageUrl,
                        UserEntity::setImageUrl
                ));

        modelMapper.typeMap(UserRegisterBindingModel.class, UserEntity.class)
                .addMappings(mapper -> mapper.map(
                        UserRegisterBindingModel::getFirstName,
                        UserEntity::setFirstName
                ))
                .addMappings(mapper -> mapper.map(
                        UserRegisterBindingModel::getLastName,
                        UserEntity::setLastName
                ))
                .addMappings(mapper -> mapper.map(
                        UserRegisterBindingModel::getUsername,
                        UserEntity::setUsername
                ))
                .addMappings(mapper -> mapper.map(
                        UserRegisterBindingModel::getEmail,
                        UserEntity::setEmail
                ))
                .addMappings(mapper -> mapper.map(
                        UserRegisterBindingModel::getBio,
                        UserEntity::setBio
                ));

        // Organizer mappings

        modelMapper.typeMap(OrganizerDTO.class, OrganizerEntity.class)
                .addMappings(mapper -> mapper.map(
                        OrganizerDTO::getName,
                        OrganizerEntity::setName
                ))
                .addMappings(mapper -> mapper.map(
                        OrganizerDTO::getHeadquarters,
                        OrganizerEntity::setHeadquarters
                ))
                .addMappings(mapper -> mapper.map(
                        OrganizerDTO::getImageUrl,
                        OrganizerEntity::setImageUrl
                ))
                .addMappings(mapper -> mapper.map(
                        OrganizerDTO::getFoundingYear,
                        OrganizerEntity::setFoundingYear
                ));

        modelMapper.typeMap(OrganizerEntity.class, OrganizerDTO.class)
                .addMappings(mapper -> mapper.map(
                        OrganizerEntity::getName,
                        OrganizerDTO::setName
                ))
                .addMappings(mapper -> mapper.map(
                        OrganizerEntity::getHeadquarters,
                        OrganizerDTO::setHeadquarters
                ))
                .addMappings(mapper -> mapper.map(
                        OrganizerEntity::getImageUrl,
                        OrganizerDTO::setImageUrl
                ))
                .addMappings(mapper -> mapper.map(
                        OrganizerEntity::getFoundingYear,
                        OrganizerDTO::setFoundingYear
                ));

        modelMapper.typeMap(OrganizerAddBindingModel.class, OrganizerEntity.class)
                .addMappings(mapper -> mapper.map(
                        OrganizerAddBindingModel::getName,
                        OrganizerEntity::setName
                ))
                .addMappings(mapper -> mapper.map(
                        OrganizerAddBindingModel::getHeadquarters,
                        OrganizerEntity::setHeadquarters
                ))
                .addMappings(mapper -> mapper.map(
                        OrganizerAddBindingModel::getFoundingYear,
                        OrganizerEntity::setFoundingYear
                ));

        // Race mappings

        modelMapper.typeMap(RaceAddBindingModel.class, RaceEntity.class)
                .addMappings(mapper -> mapper.map(
                        RaceAddBindingModel::getName,
                        RaceEntity::setName
                ))
                .addMappings(mapper -> mapper.map(
                        RaceAddBindingModel::getType,
                        RaceEntity::setType
                ))
                .addMappings(mapper -> mapper.map(
                        RaceAddBindingModel::getDescription,
                        RaceEntity::setDescription
                ))
                .addMappings(mapper -> mapper.map(
                        RaceAddBindingModel::getDate,
                        RaceEntity::setDate
                ))
                .addMappings(mapper -> mapper.map(
                        RaceAddBindingModel::getPrize,
                        RaceEntity::setPrize
                ));

        modelMapper.typeMap(RaceEntity.class, RaceDTO.class)
                .addMappings(mapper -> mapper.map(
                        RaceEntity::getName,
                        RaceDTO::setName
                ))
                .addMappings(mapper -> mapper.map(
                        RaceEntity::getType,
                        RaceDTO::setType
                ))
                .addMappings(mapper -> mapper.map(
                        entity -> entity.getTrack().getName(),
                        RaceDTO::setTrack
                ))
                .addMappings(mapper -> mapper.map(
                        RaceEntity::getDescription,
                        RaceDTO::setDescription
                ))
                .addMappings(mapper -> mapper.map(
                        RaceEntity::getDate,
                        RaceDTO::setDate
                ))
                .addMappings(mapper -> mapper.map(
                        RaceEntity::getPrize,
                        RaceDTO::setPrize
                ))
                .addMappings(mapper -> mapper.map(
                        RaceEntity::getOrganizer,
                        RaceDTO::setOrganizer
                ));

        modelMapper.typeMap(RaceEntity.class, DisplayRaceDTO.class)
                .addMappings(mapper -> mapper.map(
                        RaceEntity::getName,
                        DisplayRaceDTO::setName
                ))
                .addMappings(mapper -> mapper.map(
                        entity -> entity.getTrack().getName(),
                        DisplayRaceDTO::setTrack
                ))
                .addMappings(mapper -> mapper.map(
                        RaceEntity::getImageUrl,
                        DisplayRaceDTO::setImageUrl
                ));

        // Track mappings

        modelMapper.typeMap(TrackEntity.class, TrackDTO.class)
                .addMappings(mapper -> mapper.map(
                        TrackEntity::getName,
                        TrackDTO::setName
                ))
                .addMappings(mapper -> mapper.map(
                        TrackEntity::getLocation,
                        TrackDTO::setLocation
                ))
                .addMappings(mapper -> mapper.map(
                        TrackEntity::getLength,
                        TrackDTO::setLength
                ))
                .addMappings(mapper -> mapper.map(
                        TrackEntity::getSurface,
                        TrackDTO::setSurface
                ))
                .addMappings(mapper -> mapper.map(
                        TrackEntity::getImageUrl,
                        TrackDTO::setImageUrl
                ));

        modelMapper.typeMap(TrackAddBindingModel.class, TrackEntity.class)
                .addMappings(mapper -> mapper.map(
                        TrackAddBindingModel::getName,
                        TrackEntity::setName
                ))
                .addMappings(mapper -> mapper.map(
                        TrackAddBindingModel::getLocation,
                        TrackEntity::setLocation
                ))
                .addMappings(mapper -> mapper.map(
                        TrackAddBindingModel::getLength,
                        TrackEntity::setLength
                ))
                .addMappings(mapper -> mapper.map(
                        TrackAddBindingModel::getSurface,
                        TrackEntity::setSurface
                ));

        // Vehicle mappings
        modelMapper.typeMap(VehicleEntity.class, VehicleDTO.class)
                .addMappings(mapper -> mapper.map(
                        VehicleEntity::getMake,
                        VehicleDTO::setMake
                ))
                .addMappings(mapper -> mapper.map(
                        VehicleEntity::getModel,
                        VehicleDTO::setModel
                ))
                .addMappings(mapper -> mapper.map(
                        VehicleEntity::getHorsepower,
                        VehicleDTO::setHorsepower
                ))
                .addMappings(mapper -> mapper.map(
                        VehicleEntity::getYear,
                        VehicleDTO::setYear
                ));

        modelMapper.typeMap(VehicleDTO.class, VehicleEntity.class)
                .addMappings(mapper -> mapper.map(
                        VehicleDTO::getMake,
                        VehicleEntity::setMake
                ))
                .addMappings(mapper -> mapper.map(
                        VehicleDTO::getModel,
                        VehicleEntity::setModel
                ))
                .addMappings(mapper -> mapper.map(
                        VehicleDTO::getHorsepower,
                        VehicleEntity::setHorsepower
                ))
                .addMappings(mapper -> mapper.map(
                        VehicleDTO::getYear,
                        VehicleEntity::setYear
                ));

        // Manufacturer mappings

        modelMapper.typeMap(ManufacturerAddBindingModel.class, ManufacturerEntity.class)
                .addMappings(mapper -> mapper.map(
                        ManufacturerAddBindingModel::getName,
                        ManufacturerEntity::setName
                ))
                .addMappings(mapper -> mapper.map(
                        ManufacturerAddBindingModel::getFoundingYear,
                        ManufacturerEntity::setFoundingYear
                ))
                .addMappings(mapper -> mapper.map(
                        ManufacturerAddBindingModel::getHeadquarters,
                        ManufacturerEntity::setHeadquarters
                ));

        modelMapper.typeMap(ManufacturerEntity.class, ManufacturerDTO.class)
                .addMappings(mapper -> mapper.map(
                        ManufacturerEntity::getName,
                        ManufacturerDTO::setName
                ))
                .addMappings(mapper -> mapper.map(
                        ManufacturerEntity::getFoundingYear,
                        ManufacturerDTO::setFoundingYear
                ))
                .addMappings(mapper -> mapper.map(
                        ManufacturerEntity::getHeadquarters,
                        ManufacturerDTO::setHeadquarters
                ))
                .addMappings(mapper -> mapper.map(
                        ManufacturerEntity::getImageUrl,
                        ManufacturerDTO::setImageUrl
                ));

        return modelMapper;
    }
}
