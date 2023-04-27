package ru.practicum.explorewithme.ewm.dto.compilation;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
public class CreateCompilationDto {
    private List<Integer> events;

    @NotNull
    private Boolean pinned;
    @NotBlank(message = "Заголовок не может быть пустым")
    private String title;
}
