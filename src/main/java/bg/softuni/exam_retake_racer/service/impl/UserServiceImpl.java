package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.ImageUploadException;
import bg.softuni.exam_retake_racer.exceptions.PasswordsDoNotMatchException;
import bg.softuni.exam_retake_racer.exceptions.UserAlreadyExistsException;
import bg.softuni.exam_retake_racer.exceptions.UserNotFoundException;
import bg.softuni.exam_retake_racer.model.dto.user.UserDTO;
import bg.softuni.exam_retake_racer.model.dto.user.UserRegisterBindingModel;
import bg.softuni.exam_retake_racer.model.entity.user.UserEntity;
import bg.softuni.exam_retake_racer.repository.UserRepository;
import bg.softuni.exam_retake_racer.service.CloudinaryService;
import bg.softuni.exam_retake_racer.service.UserService;
import bg.softuni.exam_retake_racer.util.AuthenticationFacade;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AuthenticationFacade authenticationFacade, CloudinaryService cloudinaryService, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationFacade = authenticationFacade;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
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

        UserEntity user = modelMapper.map(userRegisterBindingModel, UserEntity.class).setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        try {
            String imageUrl = cloudinaryService.uploadFile(userRegisterBindingModel.getImage(), "users/" + user.getUsername());
            user.setImageUrl(imageUrl);
        } catch (Exception e) {
            throw new ImageUploadException();
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

    private UserDTO map(UserEntity entity) {
        return modelMapper.map(entity, UserDTO.class);
    }
}
