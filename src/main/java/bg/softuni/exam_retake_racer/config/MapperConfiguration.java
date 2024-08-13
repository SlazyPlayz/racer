package bg.softuni.exam_retake_racer.config;

import bg.softuni.exam_retake_racer.model.dto.race.organizer.OrganizerAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.organizer.OrganizerDTO;
import bg.softuni.exam_retake_racer.model.dto.user.UserDTO;
import bg.softuni.exam_retake_racer.model.dto.user.UserRegisterBindingModel;
import bg.softuni.exam_retake_racer.model.entity.race.OrganizerEntity;
import bg.softuni.exam_retake_racer.model.entity.user.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
                ))
                .addMappings(mapper -> mapper.map(
                        OrganizerDTO::getIdentificationNumber,
                        OrganizerEntity::setIdentificationNumber
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
                ))
                .addMappings(mapper -> mapper.map(
                        OrganizerEntity::getIdentificationNumber,
                        OrganizerDTO::setIdentificationNumber
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
                ))
                .addMappings(mapper -> mapper.map(
                        OrganizerAddBindingModel::getIdentificationNumber,
                        OrganizerEntity::setIdentificationNumber
                ));

        return modelMapper;
    }
}
