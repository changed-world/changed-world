package cmc.changedworld.api.empathy;

import cmc.changedworld.api.empathy.model.GetEmpathyRes;
import cmc.changedworld.api.empathy.model.PostEmpathyReq;
import cmc.changedworld.api.empathy.model.PostEmpathyRes;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.config.BaseResponse;
import cmc.changedworld.service.EmpathyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class EmpathyController {
    private final EmpathyService empathyService;

    @ApiOperation(value = "반성해요 이모티콘 첨부", notes = "반성해요 이모티콘을 첨부합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true, dataType = "Long", paramType = "body"),
            @ApiImplicitParam(name = "postId", value = "게시글 아이디", required = true, dataType = "Long", paramType = "body")
    })
    @ResponseBody
    @PostMapping("/empathy")
    public BaseResponse<PostEmpathyRes> createEmpathy(@Valid @RequestBody PostEmpathyReq postEmpathyReq) throws BaseException {
        Long userId = postEmpathyReq.userId;
        Long postId = postEmpathyReq.postId;

        try{
            PostEmpathyRes postEmpathyRes = empathyService.insertEmpathy(userId, postId);
            return new BaseResponse<>(postEmpathyRes);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @ApiOperation(value = "게시글 별 반성해요 이모티콘 개수 조회", notes = "게시글 별로 선택된 반성해요 이모티콘 개수를 조회합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "게시글 아이디", required = true, dataType = "Long", paramType = "path")
    })
    @ResponseBody
    @GetMapping("/empathy/{postId}")
    public BaseResponse<GetEmpathyRes> countEmpathy(@PathVariable("postId") Long postId) throws BaseException {
        try{
            GetEmpathyRes getEmpathyRes = empathyService.selectEmpathy(postId);
            return new BaseResponse<>(getEmpathyRes);
        } catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }


}
