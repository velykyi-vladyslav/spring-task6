package velykyi.vladyslav.task4.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto {
    @Size(min = 1, max = 30, message = "login must be between 1 and 30 characters")
    @NonNull
    private String name;

    public RoleDto() {
    }

    public RoleDto(
            @Size(min = 1, max = 30, message = "login must be between 1 and 30 characters")
            @NonNull String name) {
        this.name = name;
    }
}
