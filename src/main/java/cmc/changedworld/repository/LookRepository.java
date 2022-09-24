package cmc.changedworld.repository;

import cmc.changedworld.domain.Look;
import cmc.changedworld.domain.Post;
import cmc.changedworld.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookRepository extends JpaRepository<Look, Long> {
    Look findByUserAndPost(User user, Post post);
}
