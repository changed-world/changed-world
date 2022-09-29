package cmc.changedworld.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "vote")
public class Vote extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @Enumerated(EnumType.STRING)
    private UserGeneration generation;

    @JoinColumn(name = "post1")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post1;

    @JoinColumn(name = "post2")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post2;

    private Long topic1Count;

    private Long topic2Count;

    public Integer calcPercent1() {
        long totalCount = this.topic1Count + this.topic2Count;
        int result = (int)((double)this.topic1Count / totalCount * 100);
        return result;
    }

    public Vote(Post post1, Post post2, UserGeneration generation) {
        this.post1 = post1;
        this.post2 = post2;
        this.generation = generation;
        this.topic1Count = 0L;
        this.topic2Count = 0L;
    }

    public void topic1CountUp() {
        this.topic1Count++;
    }

    public void topic2CountUp() {
        this.topic2Count++;
    }
}
