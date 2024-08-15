package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.ImageUploadException;
import bg.softuni.exam_retake_racer.exceptions.OrganizerWithNameNotFoundException;
import bg.softuni.exam_retake_racer.model.dto.race.DisplayRaceDTO;
import bg.softuni.exam_retake_racer.model.dto.race.organizer.OrganizerAddBindingModel;
import bg.softuni.exam_retake_racer.model.dto.race.organizer.OrganizerDTO;
import bg.softuni.exam_retake_racer.model.entity.race.OrganizerEntity;
import bg.softuni.exam_retake_racer.model.entity.race.RaceEntity;
import bg.softuni.exam_retake_racer.repository.OrganizerRepository;
import bg.softuni.exam_retake_racer.repository.RaceRepository;
import bg.softuni.exam_retake_racer.service.CloudinaryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrganizerServiceImplTest {

    @Mock
    private OrganizerRepository organizerRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CloudinaryService cloudinaryService;

    @Mock
    private RaceRepository raceRepository;

    @InjectMocks
    private OrganizerServiceImpl organizerService;

    @Test
    void testAddOrganizerSuccess() throws IOException {
        OrganizerAddBindingModel bindingModel = new OrganizerAddBindingModel();
        bindingModel.setName("Test Organizer");

        OrganizerEntity organizerEntity = new OrganizerEntity();
        organizerEntity.setName("Test Organizer");

        when(organizerRepository.findOrganizerByName(anyString())).thenReturn(Optional.empty());
        when(modelMapper.map(bindingModel, OrganizerEntity.class)).thenReturn(organizerEntity);
        when(cloudinaryService.uploadFile(any(MultipartFile.class), anyString())).thenReturn("http://example.com/image.jpg");

        organizerService.addOrganizer(bindingModel);

        verify(organizerRepository, times(1)).save(any(OrganizerEntity.class));
    }

    @Test
    void testAddOrganizerNameExists() {
        OrganizerAddBindingModel bindingModel = new OrganizerAddBindingModel();
        bindingModel.setName("Test Organizer");

        when(organizerRepository.findOrganizerByName(anyString())).thenReturn(Optional.of(new OrganizerEntity()));

        assertThrows(OrganizerWithNameNotFoundException.class, () -> organizerService.addOrganizer(bindingModel));
    }

    @Test
    void testAddOrganizerImageUploadFails() throws IOException {
        OrganizerAddBindingModel bindingModel = new OrganizerAddBindingModel();
        bindingModel.setName("Test Organizer");

        MultipartFile mockFile = new MockMultipartFile("image", new byte[0]);
        bindingModel.setImage(mockFile);

        when(organizerRepository.findOrganizerByName(anyString())).thenReturn(Optional.empty());
        when(cloudinaryService.uploadFile(any(MultipartFile.class), anyString())).thenThrow(IOException.class);

        assertThrows(ImageUploadException.class, () -> organizerService.addOrganizer(bindingModel));
    }

    @Test
    void testGetRacesByOrganizerName() {
        String organizerName = "Test Organizer";
        DisplayRaceDTO displayRaceDTO = new DisplayRaceDTO();
        Set<DisplayRaceDTO> races =  Set.of(displayRaceDTO);

        when(raceRepository.findByOrganizerName(anyString())).thenReturn(Optional.of(new RaceEntity()));
        when(modelMapper.map(any(), eq(DisplayRaceDTO.class))).thenReturn(displayRaceDTO);

        Set<DisplayRaceDTO> result = organizerService.getRacesByOrganizerName(organizerName);

        assertEquals(races, result);
    }

    @Test
    void testGetOrganizerByNameSuccess() {
        String name = "Test Organizer";
        OrganizerEntity organizerEntity = new OrganizerEntity();
        organizerEntity.setName(name);

        OrganizerDTO organizerDTO = new OrganizerDTO();
        organizerDTO.setName(name);

        when(organizerRepository.findOrganizerByName(anyString())).thenReturn(Optional.of(organizerEntity));
        when(modelMapper.map(any(OrganizerEntity.class), eq(OrganizerDTO.class))).thenReturn(organizerDTO);

        OrganizerDTO result = organizerService.getOrganizerByName(name);

        assertEquals(name, result.getName());
    }

    @Test
    void testGetOrganizerByNameNotFound() {
        String name = "Test Organizer";

        when(organizerRepository.findOrganizerByName(anyString())).thenReturn(Optional.empty());

        assertThrows(OrganizerWithNameNotFoundException.class, () -> organizerService.getOrganizerByName(name));
    }

    @Test
    void testGetOrganizerBySearchNameSuccess() {
        String searchName = "test-organizer";
        OrganizerEntity organizerEntity = new OrganizerEntity();
        organizerEntity.setSearchName(searchName);

        OrganizerDTO organizerDTO = new OrganizerDTO();
        organizerDTO.setName("Test Organizer");

        when(organizerRepository.findOrganizerBySearchName(anyString())).thenReturn(Optional.of(organizerEntity));
        when(modelMapper.map(any(OrganizerEntity.class), eq(OrganizerDTO.class))).thenReturn(organizerDTO);

        OrganizerDTO result = organizerService.getOrganizerBySearchName(searchName);

        assertEquals("Test Organizer", result.getName());
    }

    @Test
    void testGetOrganizerBySearchNameNotFound() {
        String searchName = "test-organizer";

        when(organizerRepository.findOrganizerBySearchName(anyString())).thenReturn(Optional.empty());

        assertThrows(OrganizerWithNameNotFoundException.class, () -> organizerService.getOrganizerBySearchName(searchName));
    }

    @Test
    void testGetAllOrganizers() {
        Set<OrganizerEntity> organizers = new HashSet<>();
        organizers.add(new OrganizerEntity());

        when(organizerRepository.findAll()).thenReturn(new ArrayList<>(organizers));
        when(modelMapper.map(any(OrganizerEntity.class), eq(OrganizerDTO.class))).thenReturn(new OrganizerDTO());

        Set<OrganizerDTO> result = organizerService.getAllOrganizers();

        assertEquals(organizers.size(), result.size());
    }

    @Test
    void testGetAllOrganizerNames() {
        Set<OrganizerEntity> organizers = new HashSet<>();
        OrganizerEntity organizerEntity = new OrganizerEntity();
        organizerEntity.setName("Test Organizer");
        organizers.add(organizerEntity);

        when(organizerRepository.findAll()).thenReturn(new ArrayList<>(organizers));

        Set<String> result = organizerService.getAllOrganizerNames();

        assertTrue(result.contains("Test Organizer"));
    }

    @Test
    void testGetPhotoIds() {
        Set<OrganizerEntity> organizers = new HashSet<>();
        OrganizerEntity organizerEntity = new OrganizerEntity();
        organizerEntity.setImageUrl("http://example.com/image.jpg");
        organizers.add(organizerEntity);

        when(organizerRepository.findAll()).thenReturn(new ArrayList<>(organizers));

        Set<String> result = organizerService.getPhotoIds();

        assertTrue(result.contains("http://example.com/image.jpg"));
    }

}