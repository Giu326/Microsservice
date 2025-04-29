package ms_giulianna.Microsservice.controller.handlers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor

public class FieldMessageDTO {

    private String message;
    private String fieldName;
}
