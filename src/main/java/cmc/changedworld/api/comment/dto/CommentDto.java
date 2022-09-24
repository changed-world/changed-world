package cmc.changedworld.api.comment.dto;

import cmc.changedworld.domain.Comment;
import cmc.changedworld.domain.Post;
import cmc.changedworld.domain.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@NoArgsConstructor
public class CommentDto {
    public Comment insertIntoPost(User user, Post post, String content) {
        Comment commentEntity = Comment.builder()
                .user(user)
                .post(post)
                .content(content)
                .build();
        return commentEntity;
    }
}
