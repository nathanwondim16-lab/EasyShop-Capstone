package org.yearup.models.authentication;

import lombok.Data;

@Data
public class ConfirmRegisteredUserDto {
    private int id;
    private String username;
    private String role;
}