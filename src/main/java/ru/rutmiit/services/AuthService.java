package ru.rutmiit.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rutmiit.dto.UserRegistrationDto;

import ru.rutmiit.models.Enum.RoleEnum;
import ru.rutmiit.models.User;
import ru.rutmiit.repositories.UserRepository;
import ru.rutmiit.repositories.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    private UserRoleRepository userRoleRepository;


    private PasswordEncoder passwordEncoder;


    public AuthService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    public void register(UserRegistrationDto registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        var userRole = userRoleRepository.
                findByRoleEnum(RoleEnum.User).orElseThrow();

        User user = new User(
                registrationDTO.getUsername(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getEmail(),
                registrationDTO.getFirstName(),
                registrationDTO.getLastName()
        );

        user.setRole(userRole);

        this.userRepository.save(user);
    }

    public User getUser(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
    public void createAdminUser() {
        User adminUser = new User(
                "Admin",
                passwordEncoder.encode("Admin"),
                "admin@example.com",
                "Admin",
                "Admin"
        );

        var adminRole = userRoleRepository.findByRoleEnum(RoleEnum.Admin).orElseThrow();
        adminUser.setRole(adminRole);

        this.userRepository.save(adminUser);
    }

}
