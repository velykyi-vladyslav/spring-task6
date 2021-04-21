package velykyi.vladyslav.task4.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class UserDto {

    private String firstName;
    private String lastName;
    @NonNull
    @Email
    private String email;
    private String password;
    private String repeatPassword;
}
