package cmc.changedworld.api.vote.dto;

import cmc.changedworld.domain.UserGeneration;
import cmc.changedworld.domain.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Hunseong-Park
 * @date : 2022-09-25
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteRequestDto {
    private String topic1;
    private String topic2;
    private UserGeneration generation;

    public Vote toEntity() {
        return new Vote(topic1, topic2, generation);
    }
}
