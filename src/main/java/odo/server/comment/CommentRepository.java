package odo.server.comment;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CommentRepository extends JpaRepository<Comment, Integer>{
        List<Comment> findAllByPostId(Integer PostId);
}
