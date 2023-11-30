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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    // public String createComment(HttpServletRequest request, @RequestBody Comment
    // comment) {
    // HttpSession session = request.getSession(false); // false로 설정하여 세션이 없으면 새로
    // 생성하지 않도록 함

    // if (session != null) {
    // String userId = (String) session.getAttribute("userId");

    // if (userId != null && !userId.isEmpty()) {
    // comment.setUserId(Integer.parseInt(userId));
    // comment.setLikes(0);
    // return commentService.createComment(comment);
    // } else {
    // System.err.println("Error: userId is null or empty.");
    // return "Error: userId is null or empty.";
    // }
    // } else {
    // System.err.println("Error: Session not found.");
    // return "Error: Session not found.";
    // }

    // }
    public String createComment(@RequestBody Comment comment) {
        comment.setLikes(0);
        return commentService.createComment(comment);
    }

    @GetMapping("/comment/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Integer commentId) {
        return commentService.getComment(commentId);
    }
}
