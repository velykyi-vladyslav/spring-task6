package velykyi.vladyslav.task4.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import velykyi.vladyslav.task4.model.Receipt;
import velykyi.vladyslav.task4.model.Status;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    boolean existsById (Long id);

    List<Receipt> findAllByParentStatus(Status status, Pageable pageable);
}
