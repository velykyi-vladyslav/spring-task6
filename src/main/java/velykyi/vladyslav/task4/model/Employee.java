package velykyi.vladyslav.task4.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.security.AuthProvider;

@Entity
@Data
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
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role parentRole;
    private int localeId;

    public Employee() {
    }

}
