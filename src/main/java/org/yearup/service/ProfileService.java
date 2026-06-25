package org.yearup.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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
}