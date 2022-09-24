package cmc.changedworld.service;

import cmc.changedworld.api.vote.dto.VoteRequestDto;
import cmc.changedworld.api.vote.dto.VoteResponseDto;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.config.BaseResponseStatus;
import cmc.changedworld.domain.*;
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
import static cmc.changedworld.domain.UserGeneration.X;
import static cmc.changedworld.domain.UserGeneration.Z;

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
    public VoteResponseDto getXCurrentVote(Long userId) throws BaseException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new BaseException(USER_ID_NOT_FOUND));

        Vote vote = voteRepository.findCurrentVote(X)
                .orElseThrow(() -> new BaseException(VOTE_NOT_OPENED));

        List<Comment> comments = commentRepository.findByVoteId(vote.getVoteId());

        BallotBox ballotBox = ballotBoxRepository.findByUserAndVote(
                user.getUserId(), vote.getVoteId())
                .orElse(null);

        return VoteResponseDto.of(vote, comments, ballotBox);
    }

    @Transactional(readOnly = true)
    public VoteResponseDto getZCurrentVote(Long userId) throws BaseException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new BaseException(USER_ID_NOT_FOUND));

        Vote vote = voteRepository.findCurrentVote(Z)
                .orElseThrow(() -> new BaseException(VOTE_NOT_OPENED));

        List<Comment> comments = commentRepository.findByVoteId(vote.getVoteId());

        BallotBox ballotBox = ballotBoxRepository.findByUserAndVote(
                        user.getUserId(), vote.getVoteId())
                .orElse(null);

        return VoteResponseDto.of(vote, comments, ballotBox);
    }

    public Long addNewVote(VoteRequestDto requestDto) {
        return voteRepository.save(requestDto.toEntity()).getVoteId();
    }
}
