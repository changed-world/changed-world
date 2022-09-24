package cmc.changedworld.api.vote.dto;

import cmc.changedworld.domain.BallotBox;
import cmc.changedworld.domain.User;
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
public class VoteCheckRequestDto {
    private Long userId;
    private Long checkTopic;

    public BallotBox toEntity(Vote vote, User user) {
        return new BallotBox(checkTopic, vote, user);
    }
}
