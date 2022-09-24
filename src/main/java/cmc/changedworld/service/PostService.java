package cmc.changedworld.service;

import cmc.changedworld.api.comment.dto.CommentDto;
import cmc.changedworld.api.post.model.*;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.domain.Comment;
import cmc.changedworld.domain.Post;
import cmc.changedworld.domain.PostType;
import cmc.changedworld.domain.User;
import cmc.changedworld.repository.CommentRepository;
import cmc.changedworld.repository.PostRepository;
import cmc.changedworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cmc.changedworld.config.BaseResponseStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentDto commentDto;

    public List<GetPostRes> getPostListByType(PostType postType) throws BaseException {
        try {
            List<Post> allByType = postRepository.findAllByType(postType);
            return allByType.stream().map(GetPostRes::from).collect(Collectors.toList());
        } catch (Exception e) {
            throw new BaseException(FAILED_TO_GET_POST_LIST_IN_SERVER);
        }

    }

    public GetPostRes getPostByPostId(Long postId) throws BaseException {
        try {
            Optional<Post> byPostId = postRepository.findByPostId(postId);
            return GetPostRes.from(byPostId.get());
        } catch (Exception e) {
            throw new BaseException(FAILED_TO_GET_POST_IN_SERVER);
        }
    }

    public GetPostRes getPostByPostId(Long postId,boolean myPage,Long commentId) throws BaseException {
        try {
            Comment comment = commentRepository.findById(commentId).get();
            comment.setCheckedFromMyPage(myPage);
            commentRepository.save(comment);
            Optional<Post> byPostId = postRepository.findByPostId(postId);
            return GetPostRes.from(byPostId.get());
        } catch (Exception e) {
            throw new BaseException(FAILED_TO_GET_POST_IN_SERVER);
        }
    }

    public PostPostRes createPost(PostPostReq postPostReq) throws BaseException {
        try {
            Post build = Post.builder()
                    .writer(userRepository.findByUserId(postPostReq.getUserId()).get())
                    .title(postPostReq.getTitle())
                    .content(postPostReq.getContent())
                    .type(PostType.valueOf(postPostReq.getPostType()))
                    .build();
            Post save = postRepository.save(build);
            return new PostPostRes(save.getPostId());
        } catch (Exception e) {
            throw new BaseException(FAILED_TO_CREAT_POST_IN_SERVER);
        }
    }

    public PostPostCommentRes createPostComment(PostPostCommentReq postPostCommentReq) throws BaseException {
        User user = userRepository.getById(postPostCommentReq.userId);
        Post post = postRepository.getById(postPostCommentReq.postId);

        if(userRepository.findById(postPostCommentReq.postId).equals(Optional.empty())){
            throw new BaseException(USER_ID_NOT_FOUND);
        }

        if(postRepository.findById(postPostCommentReq.postId).equals(Optional.empty())){
            throw new BaseException(FAILED_TO_GET_POST_IN_SERVER);
        }

        try {
            Long commentId = commentRepository.save(commentDto.insertIntoPost(user, post, postPostCommentReq.content)).getCommentId();
            return new PostPostCommentRes(commentId);
        } catch (Exception e) {
            throw new BaseException(FAILED_TO_CREAT_POST_IN_SERVER);
        }
    }

}
