package com.example.timesheet.auth;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.timesheet.config.JwtService;
import com.example.timesheet.role.Role;
import com.example.timesheet.role.RoleRepository;
import com.example.timesheet.user.User;
import com.example.timesheet.user.UserRepository;
import com.example.timesheet.user_role.UserRole;
import com.example.timesheet.user_role.UserRoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(User request) {
        var user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .dob(new java.sql.Date(request.getDob().getTime()))
                .gender(request.getGender())
                .email(request.getEmail())
                .address(request.getAddress())
                .user_type(request.getUser_type())
                .level(request.getLevel())
                .position_id(request.getPosition_id())
                .branches_id(request.getBranches_id())
                .trainner_id(request.getTrainner_id())
                .salary(request.getSalary())
                .state(request.getState())
                .build();
        userRepository.save(user);
        Optional<Role> defaultRoleOptional = roleRepository.findById(2);
        Role defaultRole = defaultRoleOptional.orElseThrow(() -> new RuntimeException("Role not found"));

        UserRole userRole = UserRole.builder()
                .user(user)
                .role(defaultRole)
                .build();
        userRoleRepository.save(userRole);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse refreshAccessToken(String refreshToken) {
        if (jwtService.isTokenValid(refreshToken) && "REFRESH".equals(jwtService.extractTokenType(refreshToken))) {
            String username = jwtService.extractUsername(refreshToken);
            UserDetails userDetails = userRepository.findByUsername(username).orElseThrow();
            String newAccessToken = jwtService.generateToken(userDetails);
            return AuthenticationResponse.builder()
                    .token(newAccessToken)
                    .build();
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }
}
