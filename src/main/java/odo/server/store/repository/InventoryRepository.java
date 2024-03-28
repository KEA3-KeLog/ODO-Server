package odo.server.store.repository;

import odo.server.store.domain.Inven;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inven,Long> {
    List<Inven> findByUserId(Long userId);
}
 