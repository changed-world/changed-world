package cmc.changedworld.api.look.dto;

import cmc.changedworld.domain.Empathy;
import cmc.changedworld.domain.Look;
import cmc.changedworld.domain.Post;
import cmc.changedworld.domain.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@NoArgsConstructor
public class LookDto {
    public Look insert(User user, Post post) {
        Look lookEntity = Look.builder()
                .user(user)
                .post(post)
                .build();
        return lookEntity;
    }
}
