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

    private String topic1;

    private String topic2;

    private Long topic1Count;

    private Long topic2Count;

    public Integer calcPercent1() {
        long totalCount = this.topic1Count + this.topic2Count;
        int result = (int)((double)this.topic1Count / totalCount * 100);
        return result;
    }

    public Vote(long voteId, String topic1, String topic2) {
        this.voteId = voteId;
        this.topic1 = topic1;
        this.topic2 = topic2;
    }
}
