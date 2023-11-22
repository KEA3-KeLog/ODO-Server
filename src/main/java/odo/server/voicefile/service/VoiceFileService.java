package odo.server.voicefile.service;

import odo.server.voicefile.dto.VoiceFile;
import odo.server.voicefile.repository.VoiceFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoiceFileService {
    @Autowired
    private VoiceFileRepository voiceFileRepository;

    public String saveVoiceFile(VoiceFile voiceFile) {
        voiceFileRepository.save(voiceFile);
        return voiceFile.getFileNewName();
    }
}
