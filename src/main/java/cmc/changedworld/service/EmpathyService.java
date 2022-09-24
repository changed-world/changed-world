package cmc.changedworld.service;

import cmc.changedworld.api.empathy.dto.EmpathyDto;
import cmc.changedworld.api.empathy.model.PostEmpathyRes;
import cmc.changedworld.config.BaseException;
import cmc.changedworld.domain.Empathy;
import cmc.changedworld.domain.Post;
import cmc.changedworld.domain.User;
import cmc.changedworld.repository.EmpathyRepository;
import cmc.changedworld.repository.PostRepository;
import cmc.changedworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static cmc.changedworld.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class EmpathyService {
    @Autowired
    private final EmpathyRepository empathyRepository;

    @Autowired
    public final UserRepository userRepository;

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final EmpathyDto empathyDto;

    public PostEmpathyRes insertEmpathy(Long userId, Long postId) throws BaseException {
        User user = userRepository.getById(userId);
        Post post = postRepository.getById(postId);

        try {
            Empathy existence = empathyRepository.findByUserAndPost(user, post);

            if(existence != null){
                throw new BaseException(POST_EMPATHY_INVALID);
            };

            Long empathyId = empathyRepository.save(empathyDto.insert(user, post)).getEmpathyId();
            return new PostEmpathyRes(empathyId);
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
