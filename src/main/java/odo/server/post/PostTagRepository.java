package odo.server.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Long> {
    @Query("SELECT pt.tag AS tag, COUNT(pt) AS tagCount FROM PostTag pt WHERE pt.userId = :userId GROUP BY pt.tag ORDER BY tagCount DESC")
    List<TagCountDTO> countTagsByUserId(@Param("userId") Long userId);

    // Define a DTO for the result
    public interface TagCountDTO {
        String getTag();
        Long getTagCount();
    }
}
 