package cmc.changedworld.repository;

import cmc.changedworld.domain.Look;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookRepository extends JpaRepository<Look, Long> {
}
