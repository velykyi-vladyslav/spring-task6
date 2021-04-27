package velykyi.vladyslav.task4.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

@Data
//@Builder(access = AccessLevel.PUBLIC)
public class Employee {

    private String login;
    //max 10
    private String password;
    //max 20
    private String firstName;
    //max 20
    private String lastName;
    private int roleId;
    private int localeId;

    public Employee(String login, String password, String firstName, String lastName, int roleId, int localeId) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleId = roleId;
        this.localeId = localeId;
    }
}
