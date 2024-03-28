package odo.server.voicefile.repository;

import java.util.List;
import odo.server.voicefile.dto.VoiceFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoiceFileRepository extends JpaRepository<VoiceFile, Integer> {
    List<VoiceFile> findByUserId(Integer UserId);
}
 