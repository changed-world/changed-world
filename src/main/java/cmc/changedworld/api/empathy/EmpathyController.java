package cmc.changedworld.api.empathy;

import cmc.changedworld.api.empathy.model.PostEmpathyReq;
import cmc.changedworld.api.empathy.model.PostEmpathyRes;
import cmc.changedworld.api.kakao.dto.UserResponseDto;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.config.BaseResponse;
import cmc.changedworld.service.AuthService;
import cmc.changedworld.service.EmpathyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class EmpathyController {
    @Autowired
    private final EmpathyService empathyService;

    @ApiOperation(value = "반성해요 이모티콘 첨부", notes = "반성해요 이모티콘 첨부입니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "accessToken",
                    value = "사용자 accessToken"
            )
    })

    @ResponseBody
    @PostMapping("/empathy")
    public BaseResponse<PostEmpathyRes> createEmpathy(@Valid @RequestBody PostEmpathyReq postEmpathyReq) throws BaseException {
        Long userId = postEmpathyReq.userId;
        Long postId = postEmpathyReq.postId;

        PostEmpathyRes postEmpathyRes = empathyService.insertEmpathy(userId, postId);
        return new BaseResponse<>(postEmpathyRes);
    }
}
