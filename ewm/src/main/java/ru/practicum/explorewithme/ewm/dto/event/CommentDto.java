package ru.practicum.explorewithme.ewm.dto.event;

import lombok.*;
import ru.practicum.explorewithme.ewm.model.event.State;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    @NotNull
    private Integer id;
    @NotBlank
    private String text;
    @NotNull
    private State state;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer eventId;
}
