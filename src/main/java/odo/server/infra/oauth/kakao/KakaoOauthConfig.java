package odo.server.infra.oauth.kakao;

import org.springframework.boot.context.properties.ConfigurationProperties;

// 인가코드를 받기위해 설정한 정보를 담고있는 클래스입니다.
// application.yml 에 oauth.kakao 로 설정된 정보를 통해 생성됩니다.
@ConfigurationProperties(prefix = "oauth.kakao")
public record KakaoOauthConfig(
        String redirectUri,
        String clientId,
        String clientSecret,
        String[] scope
) {
}
