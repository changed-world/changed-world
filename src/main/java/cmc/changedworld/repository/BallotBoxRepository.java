package cmc.changedworld.repository;

import cmc.changedworld.domain.BallotBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallotBoxRepository extends JpaRepository<BallotBox, Long> {
}
