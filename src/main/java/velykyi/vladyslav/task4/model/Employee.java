package velykyi.vladyslav.task4.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Builder(access = AccessLevel.PUBLIC)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String login;
    //max 10
    private String password;
    //max 20
    private String firstName;
    //max 20
    private String lastName;
//    @ManyToOne
//    @JoinColumn(name = "role")
    private int role;
    private int localeId;


    public Employee() {
    }

    public Employee(Long id, String login, String password, String firstName, String lastName, int role, int localeId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.localeId = localeId;
    }
}
