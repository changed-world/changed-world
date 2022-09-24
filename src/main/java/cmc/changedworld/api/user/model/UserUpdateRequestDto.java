package cmc.changedworld.api.user.model;

import cmc.changedworld.domain.UserGeneration;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Hunseong-Park
 * @date : 2022-09-25
 */
@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private Long userId;
    private String nickname;
    private UserGeneration generation;
}
