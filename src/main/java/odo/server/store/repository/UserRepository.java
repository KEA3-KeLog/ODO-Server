package odo.server.store.repository;

import odo.server.store.DTO.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
