package cmc.changedworld.domain;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User writer;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private PostType type;

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Empathy> empathies = new HashSet<>();

}
