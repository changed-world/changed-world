package cmc.changedworld.api.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostPostReq {
    private String title;
    private String content;
    private String postType;
}
