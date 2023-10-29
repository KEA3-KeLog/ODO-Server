package odo.server.infra.oauth.kakao.authcode;

import lombok.RequiredArgsConstructor;
import odo.server.domain.OauthServerType;
import odo.server.domain.authcode.AuthCodeRequestUrlProvider;
import odo.server.infra.oauth.kakao.KakaoOauthConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class KakaoOauthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {
    private final KakaoOauthConfig kakaoOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.KAKAO;
    }

    @Override
    public String provide() {
        // 인가코드를 받기위한 URL 입니다
        return UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com/oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", kakaoOauthConfig.clientId())
                .queryParam("redirect_uri", kakaoOauthConfig.redirectUri())
                .queryParam("scope", String.join(",", kakaoOauthConfig.scope()))
                .toUriString();
    }
}
