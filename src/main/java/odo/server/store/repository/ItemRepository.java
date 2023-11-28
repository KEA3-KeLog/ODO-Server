package odo.server.store.repository;

import odo.server.store.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByIdIn(List<Long> ID);
}
