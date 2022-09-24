package cmc.changedworld.api.user;

import cmc.changedworld.api.user.model.GetUserPageRes;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.config.BaseResponse;
import cmc.changedworld.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public BaseResponse<GetUserPageRes> getUserPageRes(@PathVariable Long userId, @RequestParam(required = false) Long voteId) {
        try {
            GetUserPageRes getUserPageRes = userService.getUserPage(userId);
            if (voteId != null) {
                getUserPageRes.setVoteInfo("회원님의 게시물이 라떼 배틀에 올라갔어요");
                getUserPageRes.setVoteId(voteId);
            }

            return new BaseResponse<>(getUserPageRes);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
