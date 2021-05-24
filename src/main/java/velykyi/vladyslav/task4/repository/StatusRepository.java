package velykyi.vladyslav.task4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velykyi.vladyslav.task4.model.Status;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByName(String name);
}
