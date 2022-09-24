package cmc.changedworld.api.user.model;

import cmc.changedworld.api.comment.dto.GetCommentNotiRes;
import cmc.changedworld.domain.Comment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetUserPageRes {
    // 코드 작성 시작
    private Long userId;
    private String imgUrl;
    private String username;
    private List<GetCommentNotiRes> commentNotiResList;
    private String voteInfo;
    private Long voteId;

    @Builder
    public GetUserPageRes(Long userId, String imgUrl, String username, List<GetCommentNotiRes> commentNotiResList) {
        this.userId = userId;
        this.imgUrl = imgUrl;
        this.username = username;
        this.commentNotiResList = commentNotiResList;
    }

}
