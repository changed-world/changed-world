package cmc.changedworld.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class BallotBox extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ballotId;

    @Column(nullable = false)
    private Long candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    @Column(nullable = false)
    private Vote vote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private User user;

    @Builder
    public BallotBox(Long candidate, Vote vote, User user) {
        this.candidate = candidate;
        this.vote = vote;
        this.user = user;
    }
}
