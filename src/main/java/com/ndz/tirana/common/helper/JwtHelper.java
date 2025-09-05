package com.ndz.tirana.common.helper;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * 生成JSON Web令牌的工具类
 */
public class JwtHelper {

    /**
     * token过期时间
     */
    private static final long tokenExpiration = 24 * 60 * 60 * 1000; // 1天
    // 加密密钥
    private static final String tokenSignKey = "3UBIlR85iIpG6kOapVQwPQZZzYtyFSq2guaElXZsNwk7vJhUCkkeZVnrxHBZjCNb";


    /**
     * 创建令牌Token
     *
     * @param userId   用户id
     * @param username 用户名
     * @return {@link String}
     */
    public static String createToken(Long userId, String username) {
        return Jwts.builder()
                .setSubject("AUTH-USER")
                // 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("username", username)
                .signWith(generateSecretKey())
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    /**
     * 从Token中得到用户id
     *
     * @param token 令牌
     * @return {@link Long}
     */
    public static Long getUserId(String token) {
        try {
            if (StrUtil.isEmpty(token)) return null;
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(generateSecretKey()).build().parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Double userId = (Double) claims.get("userId");
            return userId.longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从Token中获得用户名
     * @param token 令牌
     * @return {@link String}
     */
    public static String getUsername(String token) {
        try {
            if (StrUtil.isEmpty(token)) return "";
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(generateSecretKey()).build().parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getUser(String token) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (StrUtil.isEmpty(token)) return null;
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(generateSecretKey()).build().parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            String username = (String) claims.get("username");
            Double userId = (Double) claims.get("userId");
            jsonObject.put("username", username);
            jsonObject.put("id", userId.longValue());
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private static SecretKey generateSecretKey() {
        byte[] encodedKey = Base64.decodeBase64(tokenSignKey);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
    }

    public static void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }

//    public static void main(String[] args) {
//        String token = JwtHelper.createToken(1L, "admin");//"eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSCjAK0A0Ndg1S0lFKrShQsjI0MzY2sDQ3MTbQUSotTi3yTFGyMjKEsP0Sc1OBWp6unfB0f7NSLQDxzD8_QwAAAA.2eCJdsJXOYaWFmPTJc8gl1YHTRl9DAeEJprKZn4IgJP9Fzo5fLddOQn1Iv2C25qMpwHQkPIGukTQtskWsNrnhQ";//JwtHelper.createToken(7L, "admin");
//        System.out.println(token);
//        System.out.println(JwtHelper.getUserId(token));
//        System.out.println(JwtHelper.getUsername(token));
//    }
}
