package velykyi.vladyslav.task4.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "decimal not null default 0.00")
    private BigDecimal bill;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status parentStatus;
}
