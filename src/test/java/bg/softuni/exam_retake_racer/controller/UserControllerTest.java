package bg.softuni.exam_retake_racer.controller;

import bg.softuni.exam_retake_racer.config.AppConfiguration;
import bg.softuni.exam_retake_racer.model.dto.user.UserDTO;
import bg.softuni.exam_retake_racer.service.CloudinaryService;
import bg.softuni.exam_retake_racer.service.RaceService;
import bg.softuni.exam_retake_racer.service.TrackService;
import bg.softuni.exam_retake_racer.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CloudinaryService cloudinaryService;

    @MockBean
    private UserService userService;

    @MockBean
    private AppConfiguration appConfiguration;

    @MockBean
    private TrackService trackService;

    @MockBean
    private RaceService raceService;

    @Test
    void testRegistration() throws Exception {

        when(cloudinaryService.uploadFile(any(MultipartFile.class), anyString())).thenReturn("https://example.com/fake-url");

        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "image",
                "test.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "test".getBytes()
        );

        mockMvc.perform(
                multipart("/users/register")
                        .file(mockMultipartFile)
                        .param("firstName", "Pesho")
                        .param("lastName", "Petrov")
                        .param("username", "pesho")
                        .param("email", "pesho@email.net")
                        .param("password", "parola1234")
                        .param("confirmPassword", "parola1234")
                        .param("bio", "I am the best")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection());
    }

    @Test
    void testLoginPage() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk());
    }

    @Test
    void testRegisterPage() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "pesho", roles = {"USER"})
    void testProfilePage() throws Exception {

        UserDTO mockUser = new UserDTO()
                .setUsername("pesho")
                .setEmail("pesho@email.net");

        when(userService.getUser()).thenReturn(mockUser);

        mockMvc.perform(get("/users/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("/users/profile"))
                .andExpect(model().attribute("user", mockUser));
    }
}