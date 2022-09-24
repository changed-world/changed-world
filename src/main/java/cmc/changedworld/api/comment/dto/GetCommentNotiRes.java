package cmc.changedworld.api.comment.dto;

import cmc.changedworld.domain.Comment;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class GetCommentNotiRes {
    private Long commentId;
    private Long postId;
    private String writerUsername;
    private final String notification
            = writerUsername + "님이 댓글을 남기셨습니다.";

    @Builder
    public GetCommentNotiRes(Long commentId, Long postId, String writerUsername) {
        this.commentId = commentId;
        this.postId = postId;
        this.writerUsername = writerUsername;
    }


    public static List<GetCommentNotiRes> from(Set<Comment> comment) {
        return comment.stream()
                .map(a -> new GetCommentNotiRes(a.getCommentId(), a.getPost().getPostId(), a.getUser().getUsername()))
                .collect(Collectors.toList());
    }

}