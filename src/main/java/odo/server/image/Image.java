package odo.server.image;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer imageId;

    @Column(name = "post_key")
    private Integer postKey;

    @Column(name = "file_ori_name")
    private String fileOriName;

    @Column(name = "file_new_name")
    private String fileNewName;


    // 생성자, 게터, 세터 생략


}

// @DynamicInsert : Insert시 Null인 필드를 제외하기위해 사용했음.
// @DynamicUpdate : update시 Null인 필드를 제외하기위해 사용했음.