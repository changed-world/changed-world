package cmc.changedworld.api.vote;

import cmc.changedworld.api.vote.dto.VoteResponseDto;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.config.BaseResponse;
import cmc.changedworld.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
