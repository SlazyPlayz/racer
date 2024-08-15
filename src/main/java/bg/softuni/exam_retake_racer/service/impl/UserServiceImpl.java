package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.exceptions.*;
import bg.softuni.exam_retake_racer.model.dto.race.DisplayRaceDTO;
import bg.softuni.exam_retake_racer.model.dto.user.UserDTO;
import bg.softuni.exam_retake_racer.model.dto.user.UserRegisterBindingModel;
import bg.softuni.exam_retake_racer.model.dto.user.UsernameChangeDTO;
import bg.softuni.exam_retake_racer.model.entity.user.ParticipantEntity;
import bg.softuni.exam_retake_racer.model.entity.user.UserEntity;
import bg.softuni.exam_retake_racer.repository.ParticipantRepository;
import bg.softuni.exam_retake_racer.repository.RaceRepository;
import bg.softuni.exam_retake_racer.repository.UserRepository;
import bg.softuni.exam_retake_racer.service.CloudinaryService;
import bg.softuni.exam_retake_racer.service.UserService;
import bg.softuni.exam_retake_racer.util.AuthenticationFacade;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final RaceRepository raceRepository;
    private final ParticipantRepository participantRepository;

    public UserServiceImpl(UserRepository userRepository, AuthenticationFacade authenticationFacade, CloudinaryService cloudinaryService, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, RaceRepository raceRepository, ParticipantRepository participantRepository) {
        this.userRepository = userRepository;
        this.authenticationFacade = authenticationFacade;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.raceRepository = raceRepository;
        this.participantRepository = participantRepository;
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
        return map(getUserEntity());
    }

    @Override
    public void changeUsername(UsernameChangeDTO usernameChangeDTO) {

        UserEntity entity = getUserEntity();
        String newUsername = usernameChangeDTO.getNewUsername();

        if(userRepository.findByUsername(newUsername).isPresent()) {
            throw new UsernameAlreadyTakenException(newUsername);
        }

        entity.setUsername(newUsername);
        userRepository.save(entity);

        UserDetails newUserDetails = userDetailsService.loadUserByUsername(newUsername);

        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                newUserDetails,
                newUserDetails.getPassword(),
                newUserDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }

    @Override
    public Set<DisplayRaceDTO> getRaces() {
        Set<ParticipantEntity> participants = participantRepository.findByUser(getUserEntity());
        return raceRepository.findAllByParticipantsContaining(participants)
                .stream()
                .map(entity -> modelMapper.map(entity, DisplayRaceDTO.class))
                .collect(Collectors.toSet());
    }

    private UserDTO map(UserEntity entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    private UserEntity getUserEntity() {
        String username = authenticationFacade.getAuthentication().getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }
}
