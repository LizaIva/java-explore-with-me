package ru.practicum.explorewithme.ewm.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;
}
