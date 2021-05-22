package velykyi.vladyslav.task4.exceptions;

import velykyi.vladyslav.task4.model.enums.ErrorType;

public class RoleNotFoundException extends  ServiceException {    private static final String DEFAULT_MESSAGE = "Role is not found!";

    public RoleNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public RoleNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
