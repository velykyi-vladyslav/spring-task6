package velykyi.vladyslav.task4.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
//@Builder(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {
    @Size(min = 2, max = 20, message = "login must be between 2 and 10 characters")
    @NonNull
    private String login;
    // Be between 8 and 40 characters long;
    // Contain at least one digit;
    // Contain at least one lower/upper case character;
    // Contain at least on special character from [ @ # $ % ! . ].
    @Pattern(regexp = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{4,20})",
            message = "login does not match the pattern")
    private String password;
    @Size(min = 2, max = 20, message = "login must be between 2 and 20 characters")
    @NonNull
    private String firstName;
    @Size(min = 2, max = 20, message = "login must be between 2 and 20 characters")
    @NonNull
    private String lastName;
    private String roleId;
    private String localeId;

    public EmployeeDto(@Size(min = 2, max = 10,
            message = "login must be between 2 and 10 characters")
                       @NonNull String login,
                       @Pattern(regexp = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{4,16})") String password,
                       @Size(min = 2, max = 20, message = "login must be between 2 and 20 characters")
                       @NonNull String firstName,
                       @Size(min = 2, max = 20, message = "login must be between 2 and 20 characters")
                       @NonNull String lastName, String roleId, String localeId) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleId = roleId;
        this.localeId = localeId;
    }
}
