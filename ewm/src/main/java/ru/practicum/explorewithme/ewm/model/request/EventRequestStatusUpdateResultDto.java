package ru.practicum.explorewithme.ewm.model.request;

import lombok.Data;
import ru.practicum.explorewithme.ewm.dto.request.RequestDto;

import java.util.List;

@Data
public class EventRequestStatusUpdateResultDto {

    private List<RequestDto> confirmedRequests;
    private List<RequestDto> rejectedRequests;
}
