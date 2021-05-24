package velykyi.vladyslav.task4.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusDto {

    @NonNull
    private String name;

    public StatusDto() {
    }
}
