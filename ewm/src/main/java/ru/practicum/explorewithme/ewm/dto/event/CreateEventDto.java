package ru.practicum.explorewithme.ewm.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventDto {
    @NotBlank
    private String annotation;
    @NotNull
    private Integer category;
    @NotBlank
    private String description;

    private LocalDateTime eventDate;
    @NotNull
    private LocationDto location;
    @NotNull
    private Boolean paid;
    @NotNull
    private Integer participantLimit;
    @NotNull
    private Boolean requestModeration;
    @NotBlank
    private String title;

    private StateAction stateAction;
}
