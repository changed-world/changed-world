package cmc.changedworld.api.empathy.dto;

import cmc.changedworld.domain.Empathy;
import cmc.changedworld.domain.Post;
import cmc.changedworld.domain.SocialType;
import cmc.changedworld.domain.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@NoArgsConstructor
public class EmpathyDto {
    public Empathy insert(User user, Post post) {
        Empathy empathyEntity = Empathy.builder()
                .user(user)
                .post(post)
                .build();
        return empathyEntity;
    }
}
