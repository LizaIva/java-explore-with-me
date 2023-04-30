package ru.practicum.explorewithme.ewm.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryDto {

    @NotBlank(message = "Название не может быть пустым")
    private String name;
}