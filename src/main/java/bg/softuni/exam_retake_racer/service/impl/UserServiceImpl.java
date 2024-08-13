package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.ImageUploadError;
import bg.softuni.exam_retake_racer.exceptions.PasswordsDoNotMatchException;
import bg.softuni.exam_retake_racer.exceptions.UserAlreadyExistsException;
import bg.softuni.exam_retake_racer.exceptions.UserNotFoundException;
import bg.softuni.exam_retake_racer.model.dto.UserDTO;
import bg.softuni.exam_retake_racer.model.dto.UserRegisterBindingModel;
import bg.softuni.exam_retake_racer.model.entity.user.UserEntity;
import bg.softuni.exam_retake_racer.repository.UserRepository;
import bg.softuni.exam_retake_racer.service.CloudinaryService;
import bg.softuni.exam_retake_racer.service.UserService;
import bg.softuni.exam_retake_racer.util.AuthenticationFacade;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;
    private final PasswordEncoder passwordEncoder;
    private final CloudinaryService cloudinaryService;

    public UserServiceImpl(UserRepository userRepository, AuthenticationFacade authenticationFacade, PasswordEncoder passwordEncoder, CloudinaryService cloudinaryService) {
        this.userRepository = userRepository;
        this.authenticationFacade = authenticationFacade;
        this.passwordEncoder = passwordEncoder;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public UserDTO register(UserRegisterBindingModel userRegisterBindingModel) {
        String username = userRegisterBindingModel.getUsername();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException(username);
        }

        if (!Objects.equals(userRegisterBindingModel.getPassword(), userRegisterBindingModel.getConfirmPassword())) {
            throw new PasswordsDoNotMatchException();
        }

        UserEntity user = new UserEntity()
                .setFirstName(userRegisterBindingModel.getFirstName())
                .setLastName(userRegisterBindingModel.getLastName())
                .setUsername(username)
                .setEmail(userRegisterBindingModel.getEmail())
                .setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()))
                .setBio(userRegisterBindingModel.getBio());

        try {
            String imageUrl = cloudinaryService.uploadFile(userRegisterBindingModel.getImage(), user.getUsername());
            user.setImageURL(imageUrl);
        } catch (Exception e) {
            throw new ImageUploadError();
        }

        UserEntity entity = userRepository.save(user);
        return map(entity);
    }

    @Override
    public UserDTO getUser() {

        String username = authenticationFacade.getAuthentication().getName();

        UserEntity entity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return map(entity);
    }

    private static UserDTO map(UserEntity entity) {
        return new UserDTO(entity.getFirstName(), entity.getLastName(), entity.getUsername(), entity.getEmail(), entity.getBio(), entity.getImageURL());
    }
}
