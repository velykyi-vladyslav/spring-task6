package velykyi.vladyslav.task4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velykyi.vladyslav.task4.model.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    boolean existsById (Long id);
}
