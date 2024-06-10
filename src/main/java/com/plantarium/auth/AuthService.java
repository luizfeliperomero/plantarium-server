package com.plantarium.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.plantarium.config.JwtService;
import com.plantarium.user.User;
import com.plantarium.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Optional<User> register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if(existingUser.isPresent()) {
            return Optional.empty();
        }
        var user = User.builder()
                .firstName(request.getFirst_name())
                .lastName(request.getLast_name())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhone_number())
                .alreadyAddedGarden(false)
                .createdWithGoogle(false)
                .build();
        return Optional.of(userRepository.save(user));
    }

    public AuthResponse authenticate(AuthRequest request) {
        var user_ = userRepository.findByEmail(request.getEmail()).orElse(null);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwt = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwt)
                .user(user)
                .build();
    }

    public Optional<AuthResponse> googleAuth(GoogleIdToken.Payload payload) {
        Optional<User> existingUserOpt = userRepository.findByEmail(payload.getEmail());
        String jwt = "";
        if(existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            if(existingUser.getCreatedWithGoogle()){
                jwt = jwtService.generateToken(existingUser);
                return Optional.of(new AuthResponse(jwt, existingUser));
            }
            return Optional.empty();
        }
        User user = User.builder()
                .createdWithGoogle(true)
                .alreadyAddedGarden(false)
                .email(payload.getEmail())
                .password(passwordEncoder.encode("test"))
                .firstName((String) payload.get("name"))
                .build();
        userRepository.save(user);
        jwt = jwtService.generateToken(user);
        System.out.println("JWT: " + jwt);
        return Optional.of(new AuthResponse(jwt, user));
    }
}
