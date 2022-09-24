package cmc.changedworld.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Empathy extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empathyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Empathy(Long empathyId, User user, Post post) {
        this.empathyId = empathyId;
        this.user = user;
        this.post = post;
    }
}
