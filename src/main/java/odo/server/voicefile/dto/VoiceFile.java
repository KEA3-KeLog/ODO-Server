package odo.server.voicefile.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="voice_file")
public class VoiceFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Integer fileId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "file_ori_name")
    private String fileOriName;

    @Column(name = "file_new_name")
    private String fileNewName;
}
 