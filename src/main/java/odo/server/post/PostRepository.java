package odo.server.post;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByUserId(Integer UserId);

    @Query("SELECT p FROM Post p WHERE DATE(p.createdTime) = CURRENT_DATE")
    List<Post> findPostsCreatedToday();

    @Query("SELECT DATE(p.createdTime) AS date, COUNT(p) AS count FROM Post p GROUP BY DATE(p.createdTime)")
    List<Object[]> getPostCountByDate();


}