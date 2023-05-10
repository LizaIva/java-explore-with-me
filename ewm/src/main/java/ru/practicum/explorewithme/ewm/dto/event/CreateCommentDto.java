package ru.practicum.explorewithme.ewm.dto.event;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@Getter
@Setter
public class CreateCommentDto {
    @NotBlank
    private String text;

    public CreateCommentDto(String text) {
        this.text = text;
    }

    public CreateCommentDto() {
    }
}
