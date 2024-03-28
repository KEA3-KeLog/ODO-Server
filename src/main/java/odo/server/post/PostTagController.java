package odo.server.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import java.util.List;

@RestController
@RequestMapping("/api/post-tags")
public class PostTagController {

    @Autowired
    private PostTagService postTagService;

    @GetMapping("/count/{userId}")
    public ResponseEntity<List<PostTagRepository.TagCountDTO>> countTagsByUserId(@PathVariable Long userId) {
        List<PostTagRepository.TagCountDTO> tagCounts = postTagService.countTagsByUserId(userId);
        return new ResponseEntity<>(tagCounts, HttpStatus.OK);
    }
}
