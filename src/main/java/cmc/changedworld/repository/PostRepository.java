package cmc.changedworld.repository;

import cmc.changedworld.domain.Post;
import cmc.changedworld.domain.PostType;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post save(Post post);

    List<Post> findAllByType(PostType postType);

    Optional<Post> findByPostId(Long postId);


}
