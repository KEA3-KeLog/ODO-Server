package odo.server.post;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

	@Autowired
	private StreakFreezeRepository StreakFreezeRepository;



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

//	private void saveTags(Post post, List<String> tagList) {
//		// Save tags in the post_tags table
//		for (String tag : tagList) {
//			PostTag postTag = new PostTag(post, tag);
//			postTagRepository.save(postTag);
//		}
//	}
protected void saveTags(Post post, Integer userId, List<String> tagList) {
	if (userId != null) {
		for (String tag : tagList) {
			PostTag postTag = new PostTag(post, tag, userId);
			postTagRepository.save(postTag);
		}
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

	public void addNewValue(Map<String, Object> newEntry) {
		try {
			// Extract data from the new entry map
			Integer count = (Integer) newEntry.get("count");
			String dateString = (String) newEntry.get("date");

			// Convert the date String to a java.util.Date object
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date parsedDate = dateFormat.parse(dateString);

			// Create a new StreakFreeze object with the extracted data
			StreakFreeze streakFreeze = new StreakFreeze();
			streakFreeze.setCount(count);
			streakFreeze.setDate(parsedDate);

			// Save the new StreakFreeze entry to the database
			StreakFreezeRepository.save(streakFreeze);
		} catch (ParseException e) {
			// Handle parsing exception
			e.printStackTrace();
		}
	}


	public List<Map<String, Object>> getCountFreeze() {
		List<Object[]> result = StreakFreezeRepository.getCountFreeze();
		List<Map<String, Object>> countFreezeList = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (Object[] row : result) {
			Date date = (Date) row[0];
			Long count = (Long) row[1];

			if (date != null) {
				Map<String, Object> countFreezeMap = new HashMap<>();
				countFreezeMap.put("date", dateFormat.format(date));
				countFreezeMap.put("count", count);
				countFreezeList.add(countFreezeMap);
			}
		}

		return countFreezeList;
	}

}