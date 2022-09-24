package cmc.changedworld.repository;

import cmc.changedworld.domain.Empathy;
import cmc.changedworld.domain.Post;
import cmc.changedworld.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpathyRepository extends JpaRepository<Empathy, Long> {
    Empathy findByUserandPost(User user, Post post);
}
