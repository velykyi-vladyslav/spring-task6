package velykyi.vladyslav.task4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velykyi.vladyslav.task4.model.Receipt;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    boolean existsById (Long id);
}
