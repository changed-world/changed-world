package cmc.changedworld.api.vote.dto;

import cmc.changedworld.domain.Comment;
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
public class VoteCommentRequestDto {
    private Long userId;
    private String content;

    public Comment toEntity(User user, Vote vote) {
        return Comment.builder()
                .content(this.content)
                .user(user)
                .vote(vote)
                .build();
    }
}
