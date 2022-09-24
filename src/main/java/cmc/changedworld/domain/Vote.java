package cmc.changedworld.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "vote")
public class Vote extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long voteId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @Column(nullable = false)
    private Post postId1;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @Column(nullable = false)
    private Post postId2;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "date", columnDefinition = "datetime", nullable = false)
    private String date;


    public Vote(long voteId, Post postId1, Post postId2, String category, String date) {
        this.voteId = voteId;
        this.postId1 = postId1;
        this.postId2 = postId2;
        this.category = category;
        this.date = date;
    }
}
