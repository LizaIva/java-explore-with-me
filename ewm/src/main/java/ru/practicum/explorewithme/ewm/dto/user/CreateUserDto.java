package ru.practicum.explorewithme.ewm.dto.user;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
public class CreateUserDto {
    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;
}
