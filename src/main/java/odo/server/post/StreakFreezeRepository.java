package odo.server.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StreakFreezeRepository extends JpaRepository<StreakFreeze, Long> {
    @Query("SELECT sf.date, COUNT(sf) FROM StreakFreeze sf GROUP BY sf.date")
    List<Object[]> getCountFreeze();
}


