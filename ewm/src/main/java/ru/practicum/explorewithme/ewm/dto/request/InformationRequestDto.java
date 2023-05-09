package ru.practicum.explorewithme.ewm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explorewithme.ewm.model.request.Status;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformationRequestDto {
    private Set<Integer> requestIds;

    private Status status;
}
