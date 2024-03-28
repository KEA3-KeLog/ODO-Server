package odo.server.voicefile;

import jakarta.servlet.http.HttpServletRequest;
import odo.server.voicefile.dto.VoiceFile;
import odo.server.voicefile.service.VoiceFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Random;
 
@RestController
public class VoiceFileController {
    @Autowired
    private VoiceFileService voiceFileService;
    // upload voice file
    @PostMapping(value = "/api/uploadvoicefile")
    public ResponseEntity<String> uploadvoicefile(
                                  @RequestParam("uploadVoiceFile") MultipartFile uploadVoiceFile,
                                  @RequestParam("userId") String userId,
                                  HttpServletRequest req) {

        // 파일 저장 경로 설정 (main/webapp/upload)
        String path = req.getServletContext().getRealPath("/upload");

        String fileOriName = uploadVoiceFile.getOriginalFilename();
        String fileNewName = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt())
                + ".mp3"; // 현재 날짜와 랜덤 정수값으로 새로운 파일명 만들기

        String filepath = path + "/" + fileNewName;

        // 파일 생성 및 복사
        File file = new File(filepath);

        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

            bos.write(uploadVoiceFile.getBytes());

            bos.close();

            // 파일 정보 DB에 저장
            VoiceFile newvoicefile = new VoiceFile();
            newvoicefile.setUserId(Integer.parseInt(userId));
            newvoicefile.setFileOriName(fileOriName);
            newvoicefile.setFileNewName(fileNewName);
            voiceFileService.saveVoiceFile(newvoicefile);

            // 저장된 이미지 URL 반환
            return ResponseEntity.ok(fileNewName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패");
        }
    }

}
