package org.yearup.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.yearup.exceptions.ProfileNotFoundExcpetion;
import org.yearup.models.Profile;
import org.yearup.repository.ProfileRepository;

@Service
@AllArgsConstructor
public class ProfileService
{
    private final ProfileRepository profileRepository;

    public Profile create(Profile profile)
    {
        return profileRepository.save(profile);
    }

    public Profile getByUserId(Integer userId) {
        return profileRepository.findById(userId).orElseThrow(() -> new ProfileNotFoundExcpetion(userId));
    }

    @Transactional
    public Profile updateProfile(Integer userId, Profile updatedProfile) {
        var currentprofile = getByUserId(userId);

        currentprofile.setFirstName(updatedProfile.getFirstName());
        currentprofile.setLastName(updatedProfile.getLastName());
        currentprofile.setPhone(updatedProfile.getPhone());
        currentprofile.setEmail(updatedProfile.getEmail());
        currentprofile.setAddress(updatedProfile.getAddress());
        currentprofile.setCity(updatedProfile.getCity());
        currentprofile.setState(updatedProfile.getState());
        currentprofile.setZip(updatedProfile.getZip());

        return profileRepository.save(currentprofile);
    }
}