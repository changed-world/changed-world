package cmc.changedworld.api.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostPostCommentReq {
    @Positive
    @NotNull
    public Long userId;

    @Positive
    @NotNull
    public Long postId;

    @Positive
    @NotNull
    public String content;
}
