package odo.server.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
 
@Service
public class CommentService {
 
    @Autowired
    private CommentR epository commentRepository;

    public List<Comment> getCommentByPostId(Integer PostId) {
        return commentRepository.findAllByPostId(PostId);
    }

    public String createComment(Comment comment) {
        commentRepository.save(comment);
        return Integer.toString(comment.getCommentId());
    }

    public ResponseEntity<Comment> getComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new ResourceNotFoundException("Not exist Post Data by id : [" + commentId + "]"));
		return ResponseEntity.ok(comment);
    }

}
