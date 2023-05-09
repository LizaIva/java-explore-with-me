package ru.practicum.explorewithme.ewm.dto.event;

import lombok.Builder;
import lombok.Data;
import ru.practicum.explorewithme.ewm.dto.category.CategoryDto;
import ru.practicum.explorewithme.ewm.dto.user.UserDto;
import ru.practicum.explorewithme.ewm.model.event.State;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class EventDto {
    @NotNull
    private Integer id;
    @NotBlank
    private String annotation;
    @NotNull
    private CategoryDto category;
    @NotNull
    private Integer confirmedRequests;
    @NotNull
    private LocalDateTime createdOn;
    @NotBlank
    private String description;
    @FutureOrPresent
    private LocalDateTime eventDate;

    @NotNull
    private UserDto initiator;
    @NotNull
    private LocationDto location;
    @NotNull
    private Boolean paid;
    @NotNull
    private Integer participantLimit;

    @NotNull
    private LocalDateTime publishedOn;

    @NotNull
    private Boolean requestModeration;

    @NotNull
    private State state;

    @NotBlank
    private String title;

    @NotNull
    private Integer views;

    private List<CommentDto> comments;
}
