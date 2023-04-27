package ru.practicum.explorewithme.ewm.dto.category;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Builder
@Data
public class CreateCategoryDto {
    @NotBlank(message = "Название не может быть пустым")
    private String name;
}