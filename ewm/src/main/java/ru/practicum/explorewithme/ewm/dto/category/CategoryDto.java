package ru.practicum.explorewithme.ewm.dto.category;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
public class CategoryDto {
    private Integer id;

    @NotEmpty
    private String name;
}
