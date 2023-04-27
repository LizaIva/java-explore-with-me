package ru.practicum.explorewithme.ewm.dto.request;

import lombok.Builder;
import lombok.Data;
import ru.practicum.explorewithme.ewm.model.request.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
public class RequestDto {

    private Integer id;
    @NotNull
    private LocalDateTime created;
    @NotNull
    private Integer event;
    @NotNull
    private Integer requester;
    @NotBlank
    private Status status;
}
