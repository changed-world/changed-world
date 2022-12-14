package cmc.changedworld.service;

import cmc.changedworld.api.empathy.dto.EmpathyDto;
import cmc.changedworld.api.empathy.model.GetEmpathyRes;
import cmc.changedworld.api.empathy.model.PostEmpathyRes;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.domain.Post;
import cmc.changedworld.domain.User;
import cmc.changedworld.repository.EmpathyRepository;
import cmc.changedworld.repository.PostRepository;
import cmc.changedworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static cmc.changedworld.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class EmpathyService {
    private final EmpathyRepository empathyRepository;
    public final UserRepository userRepository;
    private final PostRepository postRepository;

    private final EmpathyDto empathyDto;

    public PostEmpathyRes insertEmpathy(Long userId, Long postId) throws BaseException {
        User user = userRepository.getById(userId);
        String generation = user.getUserGeneration().toString();
        Post post = postRepository.getById(postId);
        String postType = post.getType().toString();

        if(empathyRepository.findByUserAndPost(user, post) != null){
            throw new BaseException(POST_EMPATHY_DUPLICATION);
        }

        if(generation == "X" && postType == "XtoX"){
            throw new BaseException(POST_EMPATHY_INVALID_GENERATION);
        }

        if(generation == "Z" && postType == "ZtoZ"){
            throw new BaseException(POST_EMPATHY_INVALID_GENERATION);
        }

        try {
            Long empathyId = empathyRepository.save(empathyDto.insert(user, post)).getEmpathyId();
            return new PostEmpathyRes(empathyId);
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetEmpathyRes selectEmpathy(Long postId) throws BaseException {
        Post post = postRepository.getById(postId);

        if(postRepository.findById(postId).equals(Optional.empty())){
            throw new BaseException(FAILED_TO_GET_POST_LIST_IN_SERVER);
        }

        try {
            Integer count = empathyRepository.countByPost(post);
            return new GetEmpathyRes(count);
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
