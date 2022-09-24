package cmc.changedworld.service;

import cmc.changedworld.api.comment.dto.GetCommentNotiRes;
import cmc.changedworld.api.kakao.dto.UserInfoDto;
import cmc.changedworld.api.user.model.GetUserPageRes;
import cmc.changedworld.api.user.model.UserUpdateRequestDto;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.domain.Comment;
import cmc.changedworld.domain.Post;
import cmc.changedworld.domain.SocialType;
import cmc.changedworld.domain.User;
import cmc.changedworld.repository.CommentRepository;
import cmc.changedworld.repository.PostRepository;
import cmc.changedworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static cmc.changedworld.config.BaseResponseStatus.*;


@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public void insertOrUpdateUser(UserInfoDto userInfoDto) {
        String socialId = userInfoDto.getSocialId();
        SocialType socialType = userInfoDto.getSocialType();
        //처음 로그인 하는 유저면 DB에 insert
        if(Boolean.FALSE.equals(findUserBySocialData(socialId, socialType).isPresent())){
            User user = userInfoDto.toEntity(); //기본 Role = ROLE.USER
            userRepository.save(user);
        }else{ //이미 로그인 했던 유저라면 DB update
            updateUserBySocialData(userInfoDto);
        }
    }

    public Optional<User> findUserBySocialData(String socialId, SocialType socialType){
        Optional<User> user = userRepository.findBySocialIdAndSocialType(socialId, socialType);
        return user;
    }

    public void updateUserBySocialData(UserInfoDto userInfo){
        userRepository.updateUserBySocialIdAndSocialType(userInfo.getUsername(), userInfo.getEmail(), userInfo.getImgURL(), userInfo.getRefreshToken() ,userInfo.getSocialId(), userInfo.getSocialType());
    }

    private List<Comment> findAllCommentByUser(Long userId) throws BaseException {
        try {
            Optional<User> byUserId = userRepository.findByUserId(userId);
            return commentRepository.findAllByUserOrderByCreatedDateTime(byUserId.get());
        } catch (Exception e) {
            throw new BaseException(FAILED_TO_GET_COMMENT_LIST_IN_SERVER);
        }
    }

    public GetUserPageRes getUserPage(Long userId) throws BaseException{
        try {
            User user = userRepository.findByUserId(userId).get();
            List<GetCommentNotiRes> getCommentNotiResList = new ArrayList<>();
            List<Post> allByWriter = postRepository.findAllByWriter(user);
            List<Set<Comment>> collect = allByWriter.stream()
                    .map(Post::getComments)
                    .collect(Collectors.toList());
            collect.stream()
                    .map(GetCommentNotiRes::from)
                    .forEach(a -> getCommentNotiResList.addAll(a));

            return GetUserPageRes.builder()
                    .userId(user.getUserId())
                    .username(user.getUsername())
                    .imgUrl(user.getImgUrl())
                    .commentNotiResList(getCommentNotiResList)
                    .build();
        } catch (Exception e) {
            throw new BaseException(FAILED_TO_GET_USER_PAGE);
        }
    }

    public Long updateUserInfo(UserUpdateRequestDto requestDto) throws BaseException {
        User user = userRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(() -> new BaseException(USER_ID_NOT_FOUND));
        user.updateInfo(requestDto);
        return user.getUserId();
    }
}
