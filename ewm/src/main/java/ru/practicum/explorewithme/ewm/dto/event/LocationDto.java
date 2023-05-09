package ru.practicum.explorewithme.ewm.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    @NotNull
    private Double lat;

    @NotNull
    private Double lon;
}
