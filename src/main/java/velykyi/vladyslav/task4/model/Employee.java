package velykyi.vladyslav.task4.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

    private String login;
    //max 10
    private String password;
    //max 20
    private String firstName;
    //max 20
    private String lastName;
    private long roleId;
    private int localeId;
}
