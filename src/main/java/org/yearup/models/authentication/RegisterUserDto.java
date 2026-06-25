package org.yearup.models.authentication;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/*
    The acronym DTO is being used for "data transfer object". It means that this type of class is specifically
    created to transfer data between the client and the server. For example, CredentialsDto represents the data a client must
    pass to the server for a login endpoint, and TokenDto represents the object that's returned from the server
    to the client from a login endpoint.
 */
@Data
public class RegisterUserDto {

    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Password is required")
    private String password;
    @NotEmpty(message = "Confirm password")
    private String confirmPassword;
    @NotEmpty(message = "Please select a role for this user.")
    private String role;

}
