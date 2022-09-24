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

    private String topic1;

    private String topic2;

    private Long topic1Count;

    private Long topic2Count;

    public Integer calcPercent1() {
        long totalCount = this.topic1Count + this.topic2Count;
        int result = (int)((double)this.topic1Count / totalCount * 100);
        return result;
    }

    public Vote(String topic1, String topic2, UserGeneration generation) {
        this.topic1 = topic1;
        this.topic2 = topic2;
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
