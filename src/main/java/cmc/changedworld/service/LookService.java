package cmc.changedworld.service;

import cmc.changedworld.api.look.dto.LookDto;
import cmc.changedworld.api.look.model.PostLookRes;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.domain.Look;
import cmc.changedworld.domain.Post;
import cmc.changedworld.domain.User;
import cmc.changedworld.repository.LookRepository;
import cmc.changedworld.repository.PostRepository;
import cmc.changedworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cmc.changedworld.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class LookService {
    private final LookRepository lookRepository;
    public final UserRepository userRepository;
    private final PostRepository postRepository;
    
    private final LookDto lookDto;

    public PostLookRes insertLook(Long userId, Long postId) throws BaseException {
        User user = userRepository.getById(userId);
        Post post = postRepository.getById(postId);

        Look existence = lookRepository.findByUserAndPost(user, post);

        if(existence != null){
            throw new BaseException(POST_LOOK_INVALID);
        };

        try {
            Long lookId = lookRepository.save(lookDto.insert(user, post)).getLookId();
            return new PostLookRes(lookId);
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
