package odo.server.post;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	private RestTemplateService restTemplateService;

	@Autowired
	private PostTagRepository postTagRepository;

	// get posts data
	public List<Post> getAllPost() {
		return postRepository.findAll();
	}

	public List<Post> getPostByUserId(Integer UserId) {
		return postRepository.findAllByUserId(UserId);
	}

	// create post
	public String createPost(Post post) {
		post.setSummary(restTemplateService.summary(post.getContents()));
		postRepository.save(post);
		return Integer.toString(post.getPostId());
	}

	private void saveTags(Post post, List<String> tagList) {
		// Save tags in the post_tags table
		for (String tag : tagList) {
			PostTag postTag = new PostTag(post, tag);
			postTagRepository.save(postTag);
		}
	}

	public ResponseEntity<Post> getPost(Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Post Data by id : [" + postId + "]"));
		return ResponseEntity.ok(post);
	}

	public ResponseEntity<Post> updatePost(Integer postId, Post updatedPost) {
		Post existingPost = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Post Data by id : [" + postId + "]"));

		existingPost.setTitle(updatedPost.getTitle());
		existingPost.setTagList(updatedPost.getTagList());
		existingPost.setContents(updatedPost.getContents());
		// Update other fields as needed

		postRepository.save(existingPost);

		return ResponseEntity.ok(existingPost);
	}

	public ResponseEntity<?> deletePost(Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Post Data by id : [" + postId + "]"));

		postRepository.delete(post);

		return ResponseEntity.ok("Post deleted successfully");
	}

	public List<Post> getPostsCreatedToday() {
		return postRepository.findPostsCreatedToday();
	}

	public List<Map<String, Object>> getPostCountByDate() {
		List<Object[]> result = postRepository.getPostCountByDate();
		List<Map<String, Object>> postCountList = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (Object[] row : result) {
			java.sql.Date date = (java.sql.Date) row[0];
			Long count = (Long) row[1];
			if (date != null) {
				Map<String, Object> postCountMap = new HashMap<>();
				postCountMap.put("date", dateFormat.format(date));
				postCountMap.put("count", count);
				postCountList.add(postCountMap);
			}
		}
		return postCountList;
	}

}