package odo.server.post;

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
public class PostController {

	@Autowired
	private PostService postService;
	private final ImageService imageService;

	@GetMapping(value = "/")
	public String home() {
		return "GCU OK";
	}

	// get all post
	@GetMapping("/post")
	public List<Post> getAllPost() {
		return postService.getAllPost();
	}

	// @GetMapping("/posts/{userId}")
	// public List<Post> getPostByUserId(
	// @PathVariable Integer userId) {

	// return postService.getPostByUserId(userId);
	// }
	@GetMapping("/posts/{userId}")
	public List<Object> getPostsAndImagesByUserId(@PathVariable Integer userId) {
		List<Post> posts = postService.getPostByUserId(userId);
		List<Object> result = new ArrayList<>();

		for (Post post : posts) {
			Integer postKey = post.getPostKey();
			System.out.println(postKey);
			String imageName = imageService.getImageByPostKey(postKey);
			// Map<String, String> imageInfo = new HashMap<>();
			// imageInfo.put("imageNewName", imageName);

			// Create a new object with Post and image information
			Map<String, Object> postWithImage = new HashMap<>();
			postWithImage.put("post", post);
			// postWithImage.put("imageInfo", imageInfo);
			postWithImage.put("fileNewName", imageName);

			result.add(postWithImage);
		}
		return result;
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
