package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.UserAlreadyExistsException;
import bg.softuni.exam_retake_racer.exceptions.UserNotFoundException;
import bg.softuni.exam_retake_racer.model.dto.UserDTO;
import bg.softuni.exam_retake_racer.model.dto.UserRegisterBindingModel;
import bg.softuni.exam_retake_racer.model.entity.UserEntity;
import bg.softuni.exam_retake_racer.repository.UserRepository;
import bg.softuni.exam_retake_racer.service.UserService;
import bg.softuni.exam_retake_racer.util.AuthenticationFacade;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

        UserEntity user = new UserEntity()
                .setFirstName(userRegisterBindingModel.firstName())
                .setLastName(userRegisterBindingModel.lastName())
                .setUsername(username)
                .setEmail(userRegisterBindingModel.email())
                .setPassword(passwordEncoder.encode(userRegisterBindingModel.password()));

        UserEntity entity = userRepository.save(user);
        return map(entity);
    }

    @Override
    public UserDTO getUser() throws Exception {

        String username = authenticationFacade.getAuthentication().getName();

        UserEntity entity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return map(entity);
    }

    private static UserDTO map(UserEntity entity) {
        return new UserDTO(entity.getFirstName(), entity.getLastName(), entity.getUsername(), entity.getEmail());
    }
}
