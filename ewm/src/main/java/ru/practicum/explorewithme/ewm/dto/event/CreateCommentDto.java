package ru.practicum.explorewithme.ewm.dto.event;

import lombok.Builder;
import lombok.Data;
import ru.practicum.explorewithme.ewm.model.event.State;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class CreateCommentDto {
    @NotBlank
    private String text;
    @NotNull
    private State state;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer eventId;
}
