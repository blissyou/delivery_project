package org.delivery.api.domain.token.helper;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.delivery.api.common.error.TokenErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.Ifs.TokenHelperIfs;
import org.delivery.api.domain.token.model.TokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper implements TokenHelperIfs {

    @Value("${token.secret.key}")
    private String secretKey;

    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;

    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;

    @Override
    public TokenDTO issueAccessToken(Map<String, Object> data) {
        var expiredLocalDateTime = LocalDateTime.now().plusHours(accessTokenPlusHour);

        var expiredAt = Date.from(
                expiredLocalDateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant()
        );

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .claims(data)
                .expiration(expiredAt)
                .compact();

        return TokenDTO.builder()
                .token(jwtToken)
                .expiresAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public TokenDTO issueRefreshToken(Map<String, Object> data) {
        var expiredLocalDateTime = LocalDateTime.now().plusHours(refreshTokenPlusHour);

        var expiredAt = Date.from(
                expiredLocalDateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant()
        );

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .claims(data)
                .expiration(expiredAt)
                .compact();

        return TokenDTO.builder()
                .token(jwtToken)
                .expiresAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var parser = Jwts.parser()
                .setSigningKey(key)
                .build();

        try{

            var result = parser.parseClaimsJws(token).getBody();

            return new HashMap<String, Object>(result);

        }catch (Exception e) {
            if( e instanceof SignatureException){
                // 유효하지 않은 토큰
                throw new ApiException(TokenErrorCode.INVALID_TOKEN,e);
            }
            else if (e instanceof ExpiredJwtException) {
                //만료된 토큰
                throw new ApiException(TokenErrorCode.EXPIRED_TOKEN,e);
            }
            else{
                //그외 에러
                throw new ApiException(TokenErrorCode.TOKEN_EXCEPTION,e);
            }

        }
    }
}
