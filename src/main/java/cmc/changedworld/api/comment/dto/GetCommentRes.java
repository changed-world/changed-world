package cmc.changedworld.api.comment.dto;

import cmc.changedworld.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GetCommentRes {

    private String username;
    private String content;
    private String imgUrl;
    private LocalDateTime localDateTime;

    @Builder
    public GetCommentRes(String username, String content, String imgUrl, LocalDateTime localDateTime) {
        this.username = username;
        this.content = content;
        this.imgUrl = imgUrl;
        this.localDateTime = localDateTime;
    }

    public static GetCommentRes from(Comment comment) {
        return GetCommentRes.builder()
                .username(comment.getUser().getUsername())
                .content(comment.getContent())
                .imgUrl(comment.getUser().getImgUrl())
                .localDateTime(comment.getCreatedDateTime())
                .build();
    }
}
