package org.yearup.controllers;

import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.yearup.mappers.UserMapper;
import org.yearup.models.Profile;
import org.yearup.service.ProfileService;
import org.yearup.service.UserService;
import org.yearup.models.authentication.LoginDto;
import org.yearup.models.authentication.LoginResponseDto;
import org.yearup.models.authentication.RegisterUserDto;
import org.yearup.models.User;
import org.yearup.security.jwt.JWTFilter;
import org.yearup.security.jwt.TokenProvider;
import java.util.Map;

@RestController
@CrossOrigin
@PreAuthorize("permitAll()")
@AllArgsConstructor
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private UserService userService;
    private final UserMapper userMapper;
    private ProfileService profileService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, false);

            User user = userService.getByUserName(loginDto.getUsername());

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return new ResponseEntity<>(new LoginResponseDto(jwt, user), httpHeaders, HttpStatus.OK);
        } catch (AuthenticationException e) {
            // bad username/password -> 401 (not a 500)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterUserDto request) {

        if(userService.exists(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("Error", "User already exists")
            );
        }

        var user = userService.create(userMapper.toUser(request));

        profileService.create(new Profile(user.getId()));

        var confirmUserDto = userMapper.toDto(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(confirmUserDto);
    }
}