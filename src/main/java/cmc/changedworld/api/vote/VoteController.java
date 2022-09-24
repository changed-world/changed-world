package cmc.changedworld.api.vote;

import cmc.changedworld.api.vote.dto.VoteCommentRequestDto;
import cmc.changedworld.api.vote.dto.VoteRequestDto;
import cmc.changedworld.api.vote.dto.VoteResponseDto;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.config.BaseResponse;
import cmc.changedworld.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Hunseong-Park
 * @date : 2022-09-24
 */
@RequestMapping("/api/v1/vote")
@RequiredArgsConstructor
@RestController
public class VoteController {

    private final VoteService voteService;

    @GetMapping("/x/{userId}")
    public BaseResponse<VoteResponseDto> getXCurrentVote(@PathVariable Long userId) {
        try {
            return new BaseResponse<>(voteService.getXCurrentVote(userId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("/z/{userId}")
    public BaseResponse<VoteResponseDto> getZCurrentVote(@PathVariable Long userId) {
        try {
            return new BaseResponse<>(voteService.getZCurrentVote(userId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PostMapping
    public BaseResponse<Map<String, Long>> addNewVote(@RequestBody VoteRequestDto requestDto) {
        Long voteId = voteService.addNewVote(requestDto);
        HashMap<String, Long> result = new HashMap<>();
        result.put("voteId", voteId);
        return new BaseResponse<>(result);
    }

    @PostMapping("/{voteId}/comment")
    public BaseResponse<Map<String, Long>> addVoteComment(
            @PathVariable Long voteId,
            @RequestBody VoteCommentRequestDto requestDto
    ) {
        try {
            Long commentId = voteService.addVoteComment(voteId, requestDto);
            HashMap<String, Long> result = new HashMap<>();
            result.put("commentId", commentId);
            return new BaseResponse<>(result);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

    }
}
