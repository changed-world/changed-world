package cmc.changedworld.repository;

import cmc.changedworld.domain.BallotBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BallotBoxRepository extends JpaRepository<BallotBox, Long> {
    @Query("select b from BallotBox b where b.user.userId = :userId and b.vote.voteId = :voteId")
    Optional<BallotBox> findByUserAndVote(Long userId, Long voteId);
}
