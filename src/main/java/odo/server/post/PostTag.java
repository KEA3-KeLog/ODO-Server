package odo.server.post;


import jakarta.persistence.*;

@Entity
@Table(name = "post_tags")
public class PostTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "tag", nullable = false)
    private String tag;

    public PostTag() {
        // Default constructor is needed by JPA
    }

    public PostTag(Post post, String tag) {
        this.post = post;
        this.tag = tag;
    }

    // Getters and setters

    // hashCode and equals methods if needed
}
