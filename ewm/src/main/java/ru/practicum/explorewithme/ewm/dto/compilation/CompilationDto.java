package ru.practicum.explorewithme.ewm.dto.compilation;

import lombok.Builder;
import lombok.Data;
import ru.practicum.explorewithme.ewm.dto.event.EventDto;
import ru.practicum.explorewithme.ewm.model.event.Event;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
public class CompilationDto {
    private Integer id;

    private List<EventDto> events;

    @NotNull
    private Boolean pinned;
    @NotBlank(message = "Заголовок не может быть пустым")
    private String title;
}
