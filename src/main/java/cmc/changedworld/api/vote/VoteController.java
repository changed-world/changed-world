package cmc.changedworld.api.vote;

import cmc.changedworld.api.vote.dto.VoteResponseDto;
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

    @GetMapping("/{userId}")
    public ResponseEntity<VoteResponseDto> getCurrentVote(@PathVariable Long userId) {
        return ResponseEntity.ok(voteService.getCurrentVote(userId));
    }
}