package odo.server.image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;




	// create post
	public String saveImage(Image image) {
		imageRepository.save(image);
		return image.getFileNewName();
	}

	public ResponseEntity<Image> getImage(Integer imageId) {
		Image image = imageRepository.findById(imageId)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Post Data by id : [" + imageId + "]"));
		return ResponseEntity.ok(image);
	}

	public String getImageByPostKey(Integer postKey) {
		List<Image> imageList = imageRepository.findAllByPostKey(postKey);
		if (imageList.isEmpty()) {
			return "error.png";
		}
		return imageList.get(0).getFileNewName();
	}


	//이미지 삭제
	public void deleteImage(Integer postKey) {
		// DB에서 이미지 정보 가져오기
		List<Image> imageList = imageRepository.findAllByPostKey(postKey);

		if (!imageList.isEmpty()) {
			Image image = imageList.get(0);

			// 파일시스템에서 이미지 파일 삭제
			try {
				Path filePath = Paths.get("./image/" + image.getFileNewName());
				Files.deleteIfExists(filePath);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("이미지 파일 삭제 실패");
			}

			// DB에서 이미지 정보 삭제
			imageRepository.delete(image);
		}
	}

}