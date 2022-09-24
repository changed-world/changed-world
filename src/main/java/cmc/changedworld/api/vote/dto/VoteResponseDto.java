package cmc.changedworld.api.vote.dto;

import cmc.changedworld.api.comment.dto.CommentResponseDto;
import cmc.changedworld.domain.BallotBox;
import cmc.changedworld.domain.Comment;
import cmc.changedworld.domain.User;
import cmc.changedworld.domain.Vote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Hunseong-Park
 * @date : 2022-09-24
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponseDto {
    private Long voteId;
    private String topic1;
    private String topic2;
    private Integer selectTopic;
    private Integer percent1;
    private Integer percent2;
    private Integer commentCount;
    private List<CommentResponseDto> comments;

    public static VoteResponseDto of(Vote vote, List<Comment> comments, BallotBox ballotBox) {
        return VoteResponseDto.builder()
                .voteId(vote.getVoteId())
                .topic1(vote.getTopic1())
                .topic2(vote.getTopic2())
                .selectTopic(ballotBox == null? 0 : ballotBox.getCandidate().intValue())
                .percent1(ballotBox == null? 0 : vote.calcPercent1())
                .percent2(ballotBox == null? 0 : 100 - vote.calcPercent1())
                .comments(ballotBox == null? Collections.emptyList() : comments.stream().map(CommentResponseDto::fromEntity).collect(Collectors.toList()))
                .commentCount(ballotBox == null? 0 : comments.size())
                .build();
    }
}
