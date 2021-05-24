package velykyi.vladyslav.task4.exceptions;

import velykyi.vladyslav.task4.model.enums.ErrorType;

public class ReceiptNotFoundException extends ServiceException{
    private static final String DEFAULT_MESSAGE = "Receipt is not found!";

    public ReceiptNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ReceiptNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
