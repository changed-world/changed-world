package cmc.changedworld.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenDto {
    private String jwtAccessToken;
    private String jwtRefreshToken;

    public TokenDto() {
    }
}
