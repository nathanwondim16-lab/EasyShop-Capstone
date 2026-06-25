package org.yearup.mappers;

import org.mapstruct.Mapper;
import org.yearup.models.User;
import org.yearup.models.authentication.ConfirmRegisteredUserDto;
import org.yearup.models.authentication.RegisterUserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterUserDto request);
    ConfirmRegisteredUserDto toDto(User user);
}
