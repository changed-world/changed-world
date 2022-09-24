package cmc.changedworld.api.look.model;

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
public class PostLookReq {
    @Positive
    @NotNull
    public Long userId;

    @Positive
    @NotNull
    public Long postId;
}
