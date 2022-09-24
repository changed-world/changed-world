package cmc.changedworld.api.comment.dto;

import cmc.changedworld.domain.Comment;
import cmc.changedworld.domain.UserGeneration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author : Hunseong-Park
 * @date : 2022-09-24
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    private String nickname;
    private UserGeneration userGeneration;
    private String content;
    private LocalDateTime createdDateTime;

    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .nickname(comment.getUser().getUsername())
                .userGeneration(comment.getUser().getUserGeneration())
                .content(comment.getContent())
                .createdDateTime(comment.getCreatedDateTime())
                .build();
    }
}
