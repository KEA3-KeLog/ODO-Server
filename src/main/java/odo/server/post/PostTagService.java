package odo.server.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostTagService {

    @Autowired
    private PostTagRepository postTagRepository;

    public List<PostTagRepository.TagCountDTO> countTagsByUserId(Long userId) {
        return postTagRepository.countTagsByUserId(userId);
    }
} 