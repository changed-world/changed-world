package cmc.changedworld.repository;

import cmc.changedworld.domain.Empathy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpathyRepository extends JpaRepository<Empathy, Long> {
}
