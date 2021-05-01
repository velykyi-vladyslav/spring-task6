package velykyi.vladyslav.task4.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import velykyi.vladyslav.task4.model.enums.ErrorType;

@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceException extends RuntimeException {

    private ErrorType errorType;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ErrorType getErrorType (){
        return ErrorType.FATAL_ERROR_TYPE;
    }
}
