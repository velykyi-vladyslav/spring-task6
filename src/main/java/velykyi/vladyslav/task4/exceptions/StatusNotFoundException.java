package velykyi.vladyslav.task4.exceptions;

import velykyi.vladyslav.task4.model.enums.ErrorType;

public class StatusNotFoundException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Status is not found!";

    public StatusNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public StatusNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
