package cmc.changedworld.api.vote.dto;

import cmc.changedworld.domain.Post;
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
    private Long post1Id;
    private Long post2Id;
    private UserGeneration generation;

}
