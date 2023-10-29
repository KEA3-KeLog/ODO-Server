package odo.server.post;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")

public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping(value = "/")
	public String home() {
		return "GCU OK";
	}

	// get all post
	@GetMapping("/post")
	public List<Post> getAllPost() {
		return postService.getAllPost();
	}

	@GetMapping("/posts/{userId}")
	public List<Post> getPostByUserId(
		@PathVariable Integer userId) {
		
		return postService.getPostByUserId(userId);
	}

	// create post
	@PostMapping("/post")
	public String createPost(@RequestBody Post post) {
		return postService.createPost(post);
	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<Post> getPostById(
			@PathVariable Integer postId) {
		
		return postService.getPost(postId);
	}
}
