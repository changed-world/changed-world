package cmc.changedworld.api.empathy.model;

import cmc.changedworld.domain.Post;
import cmc.changedworld.domain.User;
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
public class PostEmpathyReq {
    @Positive
    @NotNull
    public Long userId;

    @Positive
    @NotNull
    public Long postId;
}
