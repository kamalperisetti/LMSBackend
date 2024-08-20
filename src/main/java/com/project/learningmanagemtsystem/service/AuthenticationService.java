package com.project.learningmanagemtsystem.service;

import com.project.learningmanagemtsystem.model.*;
import com.project.learningmanagemtsystem.repository.CoursesJpaRepository;
import com.project.learningmanagemtsystem.repository.CoursesRepository;
import com.project.learningmanagemtsystem.repository.TokenRepository;
import com.project.learningmanagemtsystem.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {
    @Autowired
    private CoursesJpaRepository coursesRepository;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    //Changed


//    public AuthenticationResponse register(User request) {
//
//        // check if user already exist. if exist than authenticate the user
//        if(repository.findByUsername(request.getUsername()).isPresent()) {
//            return new AuthenticationResponse(null, null,"User already exist", null);
//        }
//
//        User user = new User();
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setUsername(request.getUsername());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setEmail(request.getEmail());
//        user.setPhoneNumber(request.getPhoneNumber());
//        user.setImageUrl(request.getImageUrl());
//        user.setRole(request.getRole());
//        user.setStudentsCompleted(request.getStudentsCompleted());
//        user.setCourses(request.getCourses());
//        user = repository.save(user);
//
//        String accessToken = jwtService.generateAccessToken(user);
//        String refreshToken = jwtService.generateRefreshToken(user);
//
//        saveUserToken(accessToken, refreshToken, user);
//
//        return new AuthenticationResponse(accessToken, refreshToken,"User registration was successful", user);
//
//    }


    public AuthenticationResponse register(User request) {
        if (request.getUsername() == null || request.getEmail() == null || request.getPhoneNumber() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or Email or Phone Number should not be null");

        }

        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        if(repository.findByPhoneNumber(request.getPhoneNumber()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone Number already Exists");
        }
        if(repository.findByEmail(request.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email Already Exists");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setImageUrl(request.getImageUrl());
        user.setRole(request.getRole());
        user.setStudentsCompleted(request.getStudentsCompleted());
        user.setCourses(request.getCourses());
        user = repository.save(user);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(accessToken, refreshToken, "User registration was successful", user);
    }


    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = repository.findByUsername(request.getUsername()).orElseThrow();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(accessToken, refreshToken, user);

        return new AuthenticationResponse(accessToken, refreshToken,   "User login was successful", user);

    }
    private void revokeAllTokenByUser(User user) {
//        CHanged Here getId to getStudentId
        List<Token> validTokens = tokenRepository.findAllAccessTokensByUser(user.getStudentId());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }
    private void saveUserToken(String accessToken, String refreshToken, User user) {
        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

    public ResponseEntity refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) {
        // extract the token from authorization header
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7);

        // extract username from token
        String username = jwtService.extractUsername(token);

        // check if the user exist in database
        User user = repository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("No user found"));

        // check if the token is valid
        if(jwtService.isValidRefreshToken(token, user)) {
            // generate access token
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            revokeAllTokenByUser(user);
            saveUserToken(accessToken, refreshToken, user);

            return new ResponseEntity(new AuthenticationResponse(accessToken, refreshToken, "New token generated", user), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);

    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public ResponseEntity<String> deleteUserById(int userId) {
        if (repository.existsById(userId)) {
            repository.deleteById(userId);
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }


    public ResponseEntity<User> updateUser(int id, User userUpdates) {
        try {
            User existingUser = repository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found With This ID"));

            if (userUpdates.getFirstName() != null) {
                existingUser.setFirstName(userUpdates.getFirstName());
            }
            if (userUpdates.getLastName() != null) {
                existingUser.setLastName(userUpdates.getLastName());
            }
            if (userUpdates.getUsername() != null) {
                existingUser.setUsername(userUpdates.getUsername());
            }

            if (userUpdates.getImageUrl() != null) {
                existingUser.setImageUrl(userUpdates.getImageUrl());
            }
            if (userUpdates.getCourses() != null) {
                // Retrieve existing courses and merge with new ones
                List<Integer> courseIds = new ArrayList<>();
                for (Courses course : userUpdates.getCourses()) {
                    courseIds.add(course.getCourseId());
                }
                List<Courses> existingCourses = coursesRepository.findAllById(courseIds);
                existingUser.setCourses(existingCourses);
            }

            repository.save(existingUser);
            return ResponseEntity.ok(existingUser);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating user", e);
        }
    }

    public ResponseEntity<AuthenticationResponse> getUserById(int userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));


        String accessToken = "";
        String refreshToken = "";

        return new ResponseEntity<>(new AuthenticationResponse(accessToken, refreshToken, "User found", user), HttpStatus.OK);
    }

}
