package cmc.changedworld.api.look;

import cmc.changedworld.api.empathy.model.PostEmpathyReq;
import cmc.changedworld.api.empathy.model.PostEmpathyRes;
import cmc.changedworld.api.look.model.PostLookReq;
import cmc.changedworld.api.look.model.PostLookRes;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.config.BaseResponse;
import cmc.changedworld.service.EmpathyService;
import cmc.changedworld.service.LookService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LookController {
    private final LookService lookService;

    @ApiOperation(value = "사용자의 게시글 조회 정보", notes = "사용자의 게시글 조회 정보를 추가합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true, dataType = "Long", paramType = "body"),
            @ApiImplicitParam(name = "postId", value = "게시글 아이디", required = true, dataType = "Long", paramType = "body")
    })
    @ResponseBody
    @PostMapping("/look")
    public BaseResponse<PostLookRes> createLook(@Valid @RequestBody PostLookReq postLookReq) throws BaseException {
        Long userId = postLookReq.userId;
        Long postId = postLookReq.postId;

        try{
            PostLookRes postLookRes = lookService.insertLook(userId, postId);
            return new BaseResponse<>(postLookRes);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
