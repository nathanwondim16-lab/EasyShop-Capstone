package org.yearup.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Profile;
import org.yearup.service.ProfileService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
@CrossOrigin
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ProfilesController {

    private final ProfileService profileService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Profile> getProfile(Principal principal) {
        var user = userService.getByUserName(principal.getName());
        var profile = profileService.getByUserId(user.getId());
        return ResponseEntity.ok(profile);
    }

    @PutMapping
    public Profile updateProfile(@RequestBody Profile profile, Principal principal) {
        var user = userService.getByUserName(principal.getName());
        return profileService.updateProfile(user.getId(), profile);
    }
}