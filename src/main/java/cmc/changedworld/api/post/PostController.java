package cmc.changedworld.api.post;

import cmc.changedworld.api.look.model.PostLookRes;
import cmc.changedworld.api.post.model.*;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.config.BaseResponse;
import cmc.changedworld.domain.PostType;
import cmc.changedworld.service.PostService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    @ApiOperation(value = "게시글 종류 별 게시글 목록 조회", notes = "XtoX, XtoY와 같은 게시글 종류 별로 게시글 목록을 조회합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "게시글 종류", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("")
    public BaseResponse<List<GetPostRes>> getPostListByType(@RequestParam String type) {
        try {
            return new BaseResponse<>(postService.getPostListByType(PostType.valueOf(type)));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ApiOperation(value = "게시글 아이디로 게시글 조회", notes = "게시글 아이디에 해당하는 게시글을 조회합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "게시글 아이디", required = true, dataType = "Long", paramType = "path")
    })
    @GetMapping("/{postId}")
    public BaseResponse<GetPostRes> getPostByPostId(@PathVariable Long postId) {
        try {
            return new BaseResponse<>(postService.getPostByPostId(postId));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true, dataType = "Long", paramType = "body"),
            @ApiImplicitParam(name = "title", value = "게시글 제목", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "content", value = "게시글 내용", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "postType", value = "게시글 종류", required = true, dataType = "String", paramType = "body")
    })
    @PostMapping("")
    public BaseResponse<PostPostRes> createPost(@RequestBody PostPostReq postPostReq) {
        try {
            return new BaseResponse<>(postService.createPost(postPostReq));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @ApiOperation(value = "게시글에 대한 댓글 작성", notes = "게시글에 대한 댓글을 작성합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "사용자 아이디", required = true, dataType = "Long", paramType = "body"),
            @ApiImplicitParam(name = "postId", value = "게시글 아이디", required = true, dataType = "Long", paramType = "body"),
            @ApiImplicitParam(name = "comment", value = "댓글 내용", required = true, dataType = "String", paramType = "body"),
    })
    @PostMapping("/comment")
    public BaseResponse<PostPostCommentRes> createPostComment(@RequestBody PostPostCommentReq postPostCommentReq) {
        try {
            PostPostCommentRes postPostCommentRes = postService.createPostComment(postPostCommentReq);
            return new BaseResponse<>(postPostCommentRes);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
