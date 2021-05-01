package velykyi.vladyslav.task4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import velykyi.vladyslav.task4.model.enums.ErrorType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Error {
    private String message;
    private ErrorType errorType;
    private LocalDateTime timeStamp;
}
