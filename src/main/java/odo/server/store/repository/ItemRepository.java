package odo.server.store.repository;

import odo.server.store.DTO.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
