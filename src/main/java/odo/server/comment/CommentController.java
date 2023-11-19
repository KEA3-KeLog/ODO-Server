package odo.server.comment;

import odo.server.image.ImageService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    @GetMapping("/comments/{postId}")
    public List<Comment> getCommentByPostId(@PathVariable Integer postId) {
        return commentService.getCommentByPostId(postId);
    }

    @PostMapping("/comment")
    public String createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Integer commentId){
        return commentService.getComment(commentId);
    }
}
