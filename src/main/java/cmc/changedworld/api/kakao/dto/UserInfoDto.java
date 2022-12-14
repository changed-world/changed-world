package cmc.changedworld.api.kakao.dto;

import cmc.changedworld.domain.SocialType;
import cmc.changedworld.domain.User;
import cmc.changedworld.domain.UserGeneration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoDto {
    private Long userId;
    private String username;
    private String email;
    private UserGeneration userGeneration;
    private String socialId;
    private String imgURL;
    private String refreshToken;
    private SocialType socialType;

    public UserInfoDto(String username, String email, UserGeneration userGeneration,String socialId, SocialType socialType, String imgURL) {
        this.username = username;
        this.email = email;
        this.userGeneration = userGeneration;
        this.socialId = socialId;
        this.socialType = socialType;
        this.imgURL = imgURL;
    }

    public User toEntity(){
        User user = new User(this.username, this.email,this.userGeneration ,this.socialId, this.imgURL, this.refreshToken, this.socialType);
        return user;
    }
}
