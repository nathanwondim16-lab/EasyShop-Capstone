package org.yearup.exceptions;

public class ProfileNotFoundExcpetion extends RuntimeException {
    public ProfileNotFoundExcpetion(Integer userId) {
        super("Profile with user id  " + userId + " was not found");
    }
}
