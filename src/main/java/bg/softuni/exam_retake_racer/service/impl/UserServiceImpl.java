package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.PasswordsDoNotMatchException;
import bg.softuni.exam_retake_racer.exceptions.UserAlreadyExistsException;
import bg.softuni.exam_retake_racer.exceptions.UserNotFoundException;
import bg.softuni.exam_retake_racer.model.dto.UserDTO;
import bg.softuni.exam_retake_racer.model.dto.UserRegisterBindingModel;
import bg.softuni.exam_retake_racer.model.entity.user.UserEntity;
import bg.softuni.exam_retake_racer.repository.UserRepository;
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

    public UserServiceImpl(UserRepository userRepository, AuthenticationFacade authenticationFacade, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationFacade = authenticationFacade;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO register(UserRegisterBindingModel userRegisterBindingModel) {
        String username = userRegisterBindingModel.username();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException(username);
        }

        if (!Objects.equals(userRegisterBindingModel.password(), userRegisterBindingModel.confirmPassword())) {
            throw new PasswordsDoNotMatchException();
        }

        UserEntity user = new UserEntity()
                .setFirstName(userRegisterBindingModel.firstName())
                .setLastName(userRegisterBindingModel.lastName())
                .setUsername(username)
                .setEmail(userRegisterBindingModel.email())
                .setPassword(passwordEncoder.encode(userRegisterBindingModel.password()))
                .setBio(userRegisterBindingModel.bio());

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
        return new UserDTO(entity.getFirstName(), entity.getLastName(), entity.getUsername(), entity.getEmail());
    }
}
