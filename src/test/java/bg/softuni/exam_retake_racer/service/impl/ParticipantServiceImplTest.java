package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.ParticipantAlreadyExistsException;
import bg.softuni.exam_retake_racer.exceptions.ParticipantNotFoundException;
import bg.softuni.exam_retake_racer.exceptions.UserNotFoundException;
import bg.softuni.exam_retake_racer.exceptions.VehicleNotFoundException;
import bg.softuni.exam_retake_racer.model.dto.user.UserDTO;
import bg.softuni.exam_retake_racer.model.dto.user.participant.ParticipantAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.user.participant.ParticipantDTO;
import bg.softuni.exam_retake_racer.model.entity.user.ParticipantEntity;
import bg.softuni.exam_retake_racer.model.entity.user.UserEntity;
import bg.softuni.exam_retake_racer.model.entity.vehicle.VehicleEntity;
import bg.softuni.exam_retake_racer.repository.ParticipantRepository;
import bg.softuni.exam_retake_racer.repository.UserRepository;
import bg.softuni.exam_retake_racer.repository.VehicleRepository;
import bg.softuni.exam_retake_racer.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class ParticipantServiceImplTest {

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ParticipantServiceImpl participantService;

    @Test
    void testAddParticipantSuccess() {
        ParticipantAddBindingModel bindingModel = new ParticipantAddBindingModel();
        bindingModel.setVehicleMake("TestMake");
        bindingModel.setVehicleModel("TestModel");

        UserEntity mockUser = new UserEntity();
        mockUser.setUsername("testUser");

        VehicleEntity mockVehicle = new VehicleEntity();
        mockVehicle.setMake("TestMake");
        mockVehicle.setModel("TestModel");

        when(userService.getUser()).thenReturn(new UserDTO().setUsername("testUser"));
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));
        when(vehicleRepository.findByMakeAndModel(anyString(), anyString())).thenReturn(Optional.of(mockVehicle));
        when(participantRepository.findByUserAndVehicle(any(), any())).thenReturn(Optional.empty());

        participantService.addParticipant(bindingModel);

        verify(participantRepository, times(1)).save(any(ParticipantEntity.class));
    }

    @Test
    void testAddParticipantParticipantAlreadyExists() {
        ParticipantAddBindingModel bindingModel = new ParticipantAddBindingModel();
        bindingModel.setVehicleMake("TestMake");
        bindingModel.setVehicleModel("TestModel");

        UserEntity mockUser = new UserEntity();
        mockUser.setUsername("testUser");

        VehicleEntity mockVehicle = new VehicleEntity();
        mockVehicle.setMake("TestMake");
        mockVehicle.setModel("TestModel");

        when(userService.getUser()).thenReturn(new UserDTO().setUsername("testUser"));
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));
        when(vehicleRepository.findByMakeAndModel(anyString(), anyString())).thenReturn(Optional.of(mockVehicle));
        when(participantRepository.findByUserAndVehicle(any(), any())).thenReturn(Optional.of(new ParticipantEntity()));

        assertThrows(ParticipantAlreadyExistsException.class, () -> participantService.addParticipant(bindingModel));
    }

    @Test
    void testRemoveParticipantSuccess() {
        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setUsername("testUser");
        participantDTO.setVehicleMake("TestMake");
        participantDTO.setVehicleModel("TestModel");

        UserEntity mockUser = new UserEntity();
        mockUser.setUsername("testUser");

        VehicleEntity mockVehicle = new VehicleEntity();
        mockVehicle.setMake("TestMake");
        mockVehicle.setModel("TestModel");

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));
        when(vehicleRepository.findByMakeAndModel(anyString(), anyString())).thenReturn(Optional.of(mockVehicle));
        when(participantRepository.findByUserAndVehicle(any(), any())).thenReturn(Optional.of(new ParticipantEntity()));

        participantService.removeParticipant(participantDTO);

        verify(participantRepository, times(1)).delete(any(ParticipantEntity.class));
    }

    @Test
    void testRemoveParticipantParticipantNotFound() {
        // Arrange
        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setUsername("testUser");
        participantDTO.setVehicleMake("TestMake");
        participantDTO.setVehicleModel("TestModel");

        UserEntity mockUser = new UserEntity();
        mockUser.setUsername("testUser");

        VehicleEntity mockVehicle = new VehicleEntity();
        mockVehicle.setMake("TestMake");
        mockVehicle.setModel("TestModel");

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));
        when(vehicleRepository.findByMakeAndModel(anyString(), anyString())).thenReturn(Optional.of(mockVehicle));
        when(participantRepository.findByUserAndVehicle(any(), any())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ParticipantAlreadyExistsException.class, () -> participantService.removeParticipant(participantDTO));
    }

    @Test
    void testGetParticipantByUsernameAndVehicleSuccess() {
        // Arrange
        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setUsername("testUser");
        participantDTO.setVehicleMake("TestMake");
        participantDTO.setVehicleModel("TestModel");

        UserEntity mockUser = new UserEntity();
        mockUser.setUsername("testUser");

        VehicleEntity mockVehicle = new VehicleEntity();
        mockVehicle.setMake("TestMake");
        mockVehicle.setModel("TestModel");

        ParticipantEntity participantEntity = new ParticipantEntity().setUser(mockUser).setVehicle(mockVehicle);

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));
        when(vehicleRepository.findByMakeAndModel(anyString(), anyString())).thenReturn(Optional.of(mockVehicle));
        when(participantRepository.findByUserAndVehicle(any(), any())).thenReturn(Optional.of(participantEntity));

        // Act
        ParticipantDTO result = participantService.getParticipantByUsernameAndVehicle(participantDTO);

        // Assert
        assertEquals(participantDTO.getUsername(), result.getUsername());
        assertEquals(participantDTO.getVehicleMake(), result.getVehicleMake());
        assertEquals(participantDTO.getVehicleModel(), result.getVehicleModel());
    }

    @Test
    void testGetParticipantByUsernameAndVehicleParticipantNotFound() {
        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setUsername("testUser");
        participantDTO.setVehicleMake("TestMake");
        participantDTO.setVehicleModel("TestModel");

        UserEntity mockUser = new UserEntity();
        mockUser.setUsername("testUser");

        VehicleEntity mockVehicle = new VehicleEntity();
        mockVehicle.setMake("TestMake");
        mockVehicle.setModel("TestModel");

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));
        when(vehicleRepository.findByMakeAndModel(anyString(), anyString())).thenReturn(Optional.of(mockVehicle));
        when(participantRepository.findByUserAndVehicle(any(), any())).thenReturn(Optional.empty());

        assertThrows(ParticipantNotFoundException.class, () -> participantService.getParticipantByUsernameAndVehicle(participantDTO));
    }

    @Test
    void testGetUserUserNotFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> participantService.getParticipantByUsernameAndVehicle(new ParticipantDTO().setUsername("unknownUser")));
    }

    @Test
    void testAddParticipantVehicleNotFound() {
        // Arrange
        ParticipantAddBindingModel bindingModel = new ParticipantAddBindingModel();
        bindingModel.setVehicleMake("TestMake");
        bindingModel.setVehicleModel("TestModel");

        UserEntity mockUser = new UserEntity();
        mockUser.setUsername("testUser");

        when(userService.getUser()).thenReturn(new UserDTO().setUsername("testUser"));
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));
        when(vehicleRepository.findByMakeAndModel(anyString(), anyString())).thenReturn(Optional.empty()); // Mock Vehicle not found

        // Act & Assert
        assertThrows(VehicleNotFoundException.class, () -> participantService.addParticipant(bindingModel));
    }
}