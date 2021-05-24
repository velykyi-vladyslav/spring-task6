package velykyi.vladyslav.task4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velykyi.vladyslav.task4.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
