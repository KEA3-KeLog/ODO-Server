package odo.server.comment;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "comment_id")
    private Integer commentId;

    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "contents")
    private String contents;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "updated_time")
    private Date updatedTime;
    
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "likes")
    private Integer likes;

        @PrePersist
    protected void onCreate() {
        createdTime = new Date();
    }
}
