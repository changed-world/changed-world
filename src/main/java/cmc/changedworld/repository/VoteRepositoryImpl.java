package cmc.changedworld.repository;

import cmc.changedworld.domain.UserGeneration;
import cmc.changedworld.domain.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.query.JpaQueryCreator;

import javax.persistence.EntityManager;
import java.util.Optional;

/**
 * @author : Hunseong-Park
 * @date : 2022-09-25
 */
@RequiredArgsConstructor
public class VoteRepositoryImpl implements VoteRepositoryCustom {

    private final EntityManager em;

    @Override
    public Optional<Vote> findCurrentVote(UserGeneration generation) {
        return Optional.of(em.createQuery("select v from Vote v " +
                "where v.generation = :generation " +
                "order by v.voteId desc", Vote.class).setParameter("generation", generation)
                .setMaxResults(1).getSingleResult());
    }
}
