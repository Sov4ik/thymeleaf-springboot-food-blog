package com.foodblog.service;

import com.foodblog.models.Role;
import com.foodblog.models.User;
import com.foodblog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {


    private final UserRepository userRepository;

    private final PasswordEncoder encoder;


    public AuthService(UserRepository userRepository,
                       PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

   /* public ResponseEntity<?> authenticateUser(String login, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }*/

    public boolean registerUser(String username,
                                          String password,
                                          String email) {

        User userFromDb = userRepository.findByUsername(username);

        if (userFromDb != null) {
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(encoder.encode(password));
        user.setEmail(email);
        userRepository.save(user);

        return true;
    }
}
