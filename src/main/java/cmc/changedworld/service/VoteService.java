package cmc.changedworld.service;

import cmc.changedworld.api.comment.dto.CommentResponseDto;
import cmc.changedworld.api.vote.dto.VoteCheckRequestDto;
import cmc.changedworld.api.vote.dto.VoteCommentRequestDto;
import cmc.changedworld.api.vote.dto.VoteRequestDto;
import cmc.changedworld.api.vote.dto.VoteResponseDto;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.domain.*;
import cmc.changedworld.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static cmc.changedworld.config.BaseResponseStatus.*;
import static cmc.changedworld.domain.UserGeneration.*;

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
    private final PostRepository postRepository;

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

    public Long addNewVote(VoteRequestDto requestDto) throws BaseException{
        Post post1 = postRepository.findByPostId(requestDto.getPost1Id()).orElseThrow(() -> new BaseException(POST_ID_NOT_FOUND));
        Post post2 = postRepository.findByPostId(requestDto.getPost2Id()).orElseThrow(() -> new BaseException(POST_ID_NOT_FOUND));

        Vote vote = new Vote(post1, post2, requestDto.getGeneration());
        return voteRepository.save(vote).getVoteId();
    }

    public CommentResponseDto addVoteComment(Long voteId, VoteCommentRequestDto requestDto) throws BaseException {
        User user = userRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(() -> new BaseException(USER_ID_NOT_FOUND));

        Vote vote = voteRepository.findById(voteId)
                .orElseThrow(() -> new BaseException(VOTE_NOT_OPENED));

        return CommentResponseDto.fromEntity(commentRepository.save(requestDto.toEntity(user, vote)));
    }

    public VoteResponseDto voteCheck(Long voteId, VoteCheckRequestDto requestDto) throws BaseException {
        User user = userRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(() -> new BaseException(USER_ID_NOT_FOUND));

        Vote vote = voteRepository.findById(voteId)
                .orElseThrow(() -> new BaseException(VOTE_NOT_OPENED));

        if (requestDto.getCheckTopic() == 1) {
            vote.topic1CountUp();
        } else {
            vote.topic2CountUp();
        }

        BallotBox ballotBox = ballotBoxRepository.save(requestDto.toEntity(vote, user));

        List<Comment> comments = commentRepository.findByVoteId(vote.getVoteId());

        return VoteResponseDto.of(vote, comments, ballotBox);
    }
}
