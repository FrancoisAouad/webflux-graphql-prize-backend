/*******************************************************************************
 *
 * Copyright (c) {2022-2023} Francois J. Aouad.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU General Public License v3.0
 * which accompanies this distribution, and is available at
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 *******************************************************************************/

package app.lib.auth.jwt;

import app.lib.auth.dto.Tokens;
import app.lib.auth.jwt.accessTokens.AccessTokenModel;
import app.lib.auth.jwt.accessTokens.AccessTokenRepository;
import app.lib.auth.jwt.refreshTokens.RefreshTokenModel;
import app.lib.auth.jwt.refreshTokens.RefreshTokenRepository;
// Services
import app.lib.users.UserModel;
import app.utils.GlobalService;
import io.jsonwebtoken.*;
// Utils
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class JwtService {
  private final AccessTokenRepository accessTokenRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final TokenRepository tokenRepository;
  private static final Logger logger =
      LoggerFactory.getLogger(JwtService.class);

  public JwtService(AccessTokenRepository accessTokenRepository,
                    RefreshTokenRepository refreshTokenRepository,
                    TokenRepository tokenRepository) {
    this.accessTokenRepository = accessTokenRepository;
    this.refreshTokenRepository = refreshTokenRepository;
    this.tokenRepository = tokenRepository;
  }

  //    @Value("${app.jwtAccessTokenSecret}")
  private String jwtAccessTokenSecret;
  //    @Value("${app.jwtRefreshTokenSecret}")
  private String jwtRefreshTokenSecret;
  //    @Value("${app.jwtEmailTokenSecret}")
  private String jwtEmailTokenSecret;
  //    @Value("${app.jwtAccessTokenExp}")
  private int jwtAccessTokenExp;
  //    @Value("${app.jwtRefreshTokenExp}")
  private int jwtRefreshTokenExp;
  //    @Value("${app.jwtEmailTokenExp}")
  private int jwtEmailTokenExp;

  public String generateJwtToken(UserModel user, JWT_TYPE type) {
    String tokenExp;
    String tokenSecret;
    switch (type) {
            case ACCESS_TOKEN -> {
                tokenExp = String.valueOf(jwtAccessTokenExp);
                tokenSecret = jwtAccessTokenSecret;
            }
            case REFRESH_TOKEN -> {
                tokenExp = String.valueOf(jwtRefreshTokenExp);
                tokenSecret = jwtRefreshTokenSecret;
            }
            case EMAIL_TOKEN -> {
                tokenExp = String.valueOf(jwtEmailTokenExp);
                tokenSecret = jwtEmailTokenSecret;
            }
            default -> throw new IllegalArgumentException("Invalid JWT_TYPE value");
        }
        Map<String, String> jwtProperties = new HashMap<>();
//        jwtProperties.put("id", user.getId());
        jwtProperties.put("token_secret", tokenSecret);
        jwtProperties.put("token_expiry", tokenExp);
        return generateToken(jwtProperties.get("id"), jwtProperties.get("token_secret"),
                jwtProperties.get("token_expiry"));
    }

    public String generateToken(String id, String tokenSecret, String expiryDate) {
        // String jwtId = UUID.randomUUID().toString();
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + Long.parseLong(expiryDate));
        return Jwts.builder()
                .setSubject(id)
                .setIssuer("note-application")
                .setIssuedAt(issuedAt)
                .setExpiration(expiresAt)
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }

    // public String getUserNameFromJwtToken(String token) {
    // return
    // Jwts.parser().setSigningKey(jwtRefreshTokenSecret).parseClaimsJws(token).getBody().getSubject();
    // }

    // public String getUserIdFromJwtToken(String token) {
    // System.out.println("BEFORE");
    // String claims =
    // Jwts.parser().setSigningKey(jwtRefreshTokenSecret).parseClaimsJws(token).getBody().getSubject();
    // System.out.println("YYESSSS" + claims);
    // return claims;
    // }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtAccessTokenSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    /**
     * @param userModel
     * @param mailToken
     * @function saveTokens - Method that creates the tokens and saves them in their
     * tables and sends them back as response
     * @returns Tokens - Object token that has the pair tokens
     */
    public Tokens saveTokens(UserModel userModel, Boolean mailToken) {
        if (mailToken) {
            String emailToken = generateJwtToken(userModel, JWT_TYPE.EMAIL_TOKEN);
            userModel.setEmailToken(emailToken);
        }
        String accessToken = generateJwtToken(userModel, JWT_TYPE.ACCESS_TOKEN);
        String refreshToken = generateJwtToken(userModel, JWT_TYPE.REFRESH_TOKEN);
        AccessTokenModel accessTokenModel = AccessTokenModel.builder()
                .id(GlobalService.generateUUID())
                .accessTokenValue(accessToken)
                .build();
        accessTokenRepository.save(accessTokenModel);
        RefreshTokenModel refreshTokenModel = RefreshTokenModel.builder()
                .id(GlobalService.generateUUID())
                .refreshTokenValue(refreshToken)
                .build();
        refreshTokenRepository.save(refreshTokenModel);
        TokenModel tokenModel = TokenModel.builder()
                .id(GlobalService.generateUUID())
                .accessToken(accessTokenModel.getId())
                .refreshToken(refreshTokenModel.getId())
                .build();
//        tokenModel.setUserId(userModel.getId());
        tokenRepository.save(tokenModel);
        return new Tokens(accessToken, refreshToken);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtAccessTokenSecret).parseClaimsJws(token).getBody();
    }

    // private Boolean isTokenExpired(String token) {
    //     return extractExpiration(token).before(new Date());
    // }
}
