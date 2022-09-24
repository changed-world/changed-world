package cmc.changedworld.api.post.model;


import cmc.changedworld.api.comment.dto.GetCommentRes;
import cmc.changedworld.domain.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class GetPostRes {

    private Long postId;
    private String title;
    private String content;
    private LocalDateTime localDateTime;
    private long empathyCount;
    private List<GetCommentRes> comments;

    @Builder
    public GetPostRes(Long postId, String title, String content, LocalDateTime localDateTime, long empathyCount, List<GetCommentRes> comments) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.localDateTime = localDateTime;
        this.empathyCount = empathyCount;
        this.comments = comments;
    }


    public static GetPostRes from(Post p) {
        return GetPostRes.builder()
                .postId(p.getPostId())
                .title(p.getTitle())
                .content(p.getContent())
                .localDateTime(p.getCreatedDateTime())
                .empathyCount(p.getEmpathies().size())
                .comments(p.getComments().stream()
                        .map(GetCommentRes::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
