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

    private String topic1;

    private String topic2;

    public Vote(long voteId, String topic1, String topic2) {
        this.voteId = voteId;
        this.topic1 = topic1;
        this.topic2 = topic2;
    }
}
