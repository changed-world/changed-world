package cmc.changedworld.service;

import cmc.changedworld.api.vote.dto.VoteResponseDto;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.config.BaseResponseStatus;
import cmc.changedworld.domain.BallotBox;
import cmc.changedworld.domain.Comment;
import cmc.changedworld.domain.User;
import cmc.changedworld.domain.Vote;
import cmc.changedworld.repository.BallotBoxRepository;
import cmc.changedworld.repository.CommentRepository;
import cmc.changedworld.repository.UserRepository;
import cmc.changedworld.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static cmc.changedworld.config.BaseResponseStatus.USER_ID_NOT_FOUND;
import static cmc.changedworld.config.BaseResponseStatus.VOTE_NOT_OPENED;

/**
 * @author : Hunseong-Park
 * @date : 2022-09-24
 */
@RequiredArgsConstructor
@Transactional
@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final BallotBoxRepository ballotBoxRepository;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public VoteResponseDto getCurrentVote(Long userId) throws BaseException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new BaseException(USER_ID_NOT_FOUND));

        Vote vote = voteRepository.findFirstByOrderByVoteIdDesc()
                .orElseThrow(() -> new BaseException(VOTE_NOT_OPENED));

        List<Comment> comments = commentRepository.findByVoteId(vote.getVoteId());

        BallotBox ballotBox = ballotBoxRepository.findByUserAndVote(
                user.getUserId(), vote.getVoteId())
                .orElse(null);

        return VoteResponseDto.of(vote, comments, ballotBox);
    }
}
