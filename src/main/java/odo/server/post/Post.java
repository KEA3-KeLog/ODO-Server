package odo.server.post;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Post {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "post_key")
    private Integer postKey;

    @Column(name = "title")
    private String title;

    @Column(name = "tag")
    private String tag;

    @Lob
    @Column(name = "", columnDefinition = "LONGTEXT")
    private String summary;

    @Lob
    @Column(name = "contents", columnDefinition = "LONGTEXT")
    private String contents;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "updated_time")
    private Date updatedTime;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "counts")
    private Integer counts;

    @Column(name = "voice_file")
    private String voiceFile;

    @ElementCollection
//    @CollectionTable(name = "post_tags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "tag")
    private List<String> tagList;




    // 생성자, 게터, 세터 생략

    //잔디밭에 쓰일 날짜
    @PrePersist
    protected void onCreate() {
        createdTime = new Date();
    }

}

// @DynamicInsert : Insert시 Null인 필드를 제외하기위해 사용했음.
// @DynamicUpdate : update시 Null인 필드를 제외하기위해 사용했음.