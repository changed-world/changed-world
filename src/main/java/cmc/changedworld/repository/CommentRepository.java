package cmc.changedworld.repository;

import cmc.changedworld.domain.Comment;
import cmc.changedworld.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUserOrderByCreatedDateTime(User user);
}
