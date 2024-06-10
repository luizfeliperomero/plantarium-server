package com.plantarium.auth;

import com.plantarium.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final GoogleIdTokenVerifier googleIdTokenVerifier;

    @PostMapping("/google-auth")
    public ResponseEntity googleAuth(@RequestParam String requestToken) {
        try {
            GoogleIdToken idToken = googleIdTokenVerifier.verify(requestToken);
            if(idToken != null) {
                Payload payload = idToken.getPayload();
                Optional<AuthResponse> authResponseOpt = authService.googleAuth(payload);
                return authResponseOpt.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity("Email already taken", HttpStatus.UNAUTHORIZED));
            }
            return new ResponseEntity("Invalid idToken", HttpStatus.UNAUTHORIZED); }
        catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity("Catch: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/register")
    public ResponseEntity register(RegisterRequest request) {
        Optional<User> user = authService.register(request);
        if(user.isPresent()) {
           return ResponseEntity.ok(user.get());
        }
        return new ResponseEntity("Email already taken", HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
