package cmc.changedworld.api.user;

import cmc.changedworld.api.user.model.GetUserPageRes;
import cmc.changedworld.api.user.model.UserUpdateRequestDto;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.config.BaseResponse;
import cmc.changedworld.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "사용자 페이지 조회", notes = "사용자 페이지에 들어갈 정보를 조회합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "voteId", value = "투표 아이디", required = true, dataType = "Long", paramType = "query")
    })
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

    // UserInfo update (닉네임, X-Z세대 여부)
    @PatchMapping
    public BaseResponse<Map<String, Long>> updateUserInfo(
            @RequestBody UserUpdateRequestDto requestDto) {
        try {
            Long userId = userService.updateUserInfo(requestDto);
            Map<String, Long> result = new HashMap();
            result.put("userId", userId);
            return new BaseResponse<>(result);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
