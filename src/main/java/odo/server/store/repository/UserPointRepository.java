package odo.server.store.repository;

import odo.server.store.domain.UserPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPointRepository extends JpaRepository<UserPoint,Long> {
}
