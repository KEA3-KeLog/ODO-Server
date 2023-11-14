package odo.server.store.repository;

import odo.server.store.DTO.Inven;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inven,Long> {
}
