package ru.practicum.explorewithme.ewm.dto.event;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class LocationDto {
    @NotNull
    private Long lat;

    @NotNull
    private Long lon;
}
