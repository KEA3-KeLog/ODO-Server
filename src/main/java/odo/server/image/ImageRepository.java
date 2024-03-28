package odo.server.image;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import odo.server.post.Post;


public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findAllByPostKey(Integer postKey);
}
 