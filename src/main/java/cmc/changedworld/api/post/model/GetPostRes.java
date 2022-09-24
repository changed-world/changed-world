package cmc.changedworld.api.post.model;


import cmc.changedworld.domain.Post;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class GetPostRes {

    private Long postId;
    private String title;
    private String content;
    private LocalDateTime localDateTime;
    private long empathyCount;

    @Builder
    public GetPostRes(Long postId, String title, String content, LocalDateTime localDateTime, long empathyCount) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.localDateTime = localDateTime;
        this.empathyCount = empathyCount;
    }

    public static GetPostRes from(Post p) {
        return GetPostRes.builder()
                .postId(p.getPostId())
                .title(p.getTitle())
                .content(p.getContent())
                .localDateTime(p.getCreatedDateTime())
                .empathyCount(p.getEmpathies().size())
                .build();
    }
}
