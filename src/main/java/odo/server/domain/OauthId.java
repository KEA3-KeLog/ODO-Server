package odo.server.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;

import static lombok.AccessLevel.PROTECTED;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
 
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class OauthId {
    // 특정 인증 서버의 식별자 값을 의미하는 oauthServerId
    @Column(nullable = false, name = "oauth_server_id")
    private String oauthServerId;

    // 이를 제공하는 서비스 타입
    @Enumerated(STRING)
    @Column(nullable = false, name = "oauth_server")
    private OauthServerType oauthServerType;

    public String oauthServerId() {
        return oauthServerId;
    }

    public OauthServerType oauthServer() {
        return oauthServerType;
    }
}

// 식별자의 중복을 예방하기 위함이랍니다.