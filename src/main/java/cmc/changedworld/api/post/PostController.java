package cmc.changedworld.api.post;

import cmc.changedworld.api.post.model.GetPostRes;
import cmc.changedworld.api.post.model.PostPostReq;
import cmc.changedworld.api.post.model.PostPostRes;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.config.BaseResponse;
import cmc.changedworld.domain.PostType;
import cmc.changedworld.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    @GetMapping("")
    public BaseResponse<List<GetPostRes>> getPostListByType(@RequestParam String type) {
        try {
            return new BaseResponse<>(postService.getPostListByType(PostType.valueOf(type)));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PostMapping("")
    public BaseResponse<PostPostRes> createPost(@RequestBody PostPostReq postPostReq) {
        try {
            return new BaseResponse<>(postService.createPost(postPostReq));
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
}
