package velykyi.vladyslav.task4.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
//    @OneToMany(mappedBy = "role")
//    private Set<Employee> employees;

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
//        this.employees = employees;
    }
}
