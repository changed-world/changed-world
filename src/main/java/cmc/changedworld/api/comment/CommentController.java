package cmc.changedworld.api.comment;

import cmc.changedworld.config.BaseResponse;
import cmc.changedworld.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    public BaseResponse<Get>
}
