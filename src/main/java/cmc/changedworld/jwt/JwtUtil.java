package cmc.changedworld.jwt;

import cmc.changedworld.api.kakao.dto.UserInfoDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class JwtUtil {

    private final int ACCESS_TOKEN_EXP_MIN = 3600; // 30 min
    private final int REFRESH_TOKEN_EXP_MIN = 604800; // 7 day

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey key;

    @PostConstruct
    protected void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }


    public TokenDto createAccessToken(UserInfoDto userInfoDto, TokenDto tokenDto) {

        // createJws: JWT를 Signature로 token을 만듦.

        String accessToken = createJws(ACCESS_TOKEN_EXP_MIN, userInfoDto);
        tokenDto.setJwtAccessToken(accessToken);

        return tokenDto; // 생성자로 객체를 만들기 때문에 @Setter 제거했습니다.
    }

    public TokenDto createRefreshToken(UserInfoDto userInfoDto) {
        // createJws: JWT를 Signature로 token을 만듦.

        String refreshToken = createJws(REFRESH_TOKEN_EXP_MIN, null);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setJwtRefreshToken(refreshToken);

        return tokenDto; // 생성자로 객체를 만들기 때문에 @Setter 제거했습니다.
    }






    private String createJws(Integer expMin, UserInfoDto userInfoDto) {

        Date NOW = new Date(); // 메서드를 호출할 때의 시간을 생성해야 정확한 시간이다. -> private static final NOW = new Date()의 경우 클래스를 로더할 때의 시간이므로 적절하지 않다.

        //Header
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "jwt");
        header.put("alg", "HS256");
        //Body(Claims)
        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", "worryrecord");
        claims.put("issueAt", NOW);
        claims.put("exp", new Date(System.currentTimeMillis() + 1000 * 60 * expMin));

        // TODO:: null 처리 코드 확인
        if(Objects.nonNull(userInfoDto))  {
            claims.put("userId", userInfoDto.getUserId());
            claims.put("socialId", userInfoDto.getSocialId());
            claims.put("socialType", userInfoDto.getSocialType().toString());
            claims.put("username", userInfoDto.getUsername());
            claims.put("email", userInfoDto.getEmail());
        }

        //Signiture
        String token = Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;

    }
}