package com.foodblog.service;

import com.foodblog.models.ERole;
import com.foodblog.models.Role;
import com.foodblog.models.User;
import com.foodblog.repository.RoleRepository;
import com.foodblog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {


    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;


    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    public void registerUser(String login,
                                          String password,
                                          String email) {

        if (userRepository.existsByUsername(login)) {
            return;
        }

        if (userRepository.existsByEmail(email)) {
            return;
        }

        // Create new user's account
        User user = new User(login,
                email,
                encoder.encode(password));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }
}
