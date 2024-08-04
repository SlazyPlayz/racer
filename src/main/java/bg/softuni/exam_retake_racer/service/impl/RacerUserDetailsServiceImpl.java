package bg.softuni.exam_retake_racer.service.impl;

import bg.softuni.exam_retake_racer.model.entity.RoleEntity;
import bg.softuni.exam_retake_racer.model.entity.UserEntity;
import bg.softuni.exam_retake_racer.repository.UserRepository;
import bg.softuni.exam_retake_racer.service.RacerUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class RacerUserDetailsServiceImpl implements RacerUserDetailsService {

    private final UserRepository userRepository;

    public RacerUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(RacerUserDetailsServiceImpl::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found!"));
    }

    private static UserDetails map(UserEntity user) {
        return User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(RacerUserDetailsServiceImpl::map).toList())
                .build();
    }

    private static GrantedAuthority map(RoleEntity role) {
        return new SimpleGrantedAuthority(
                "ROLE_" + role.getRole().name()
        );
    }
}
