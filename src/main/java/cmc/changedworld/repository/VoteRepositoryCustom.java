package cmc.changedworld.repository;

import cmc.changedworld.domain.UserGeneration;
import cmc.changedworld.domain.Vote;

import java.util.Optional;

/**
 * @author : Hunseong-Park
 * @date : 2022-09-25
 */
public interface VoteRepositoryCustom {
    Optional<Vote> findCurrentVote(UserGeneration generation);
}
