package velykyi.vladyslav.task4.exceptions;

import velykyi.vladyslav.task4.model.enums.ErrorType;

public class EmployeeNotFoundException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Employee is not found!";

    public EmployeeNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
