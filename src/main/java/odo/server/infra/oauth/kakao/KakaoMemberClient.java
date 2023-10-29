package odo.server.infra.oauth.kakao;

import lombok.RequiredArgsConstructor;
import odo.server.domain.OauthMember;
import odo.server.domain.OauthServerType;
import odo.server.domain.client.OauthMemberClient;
import odo.server.infra.oauth.kakao.client.KakaoApiClient;
import odo.server.infra.oauth.kakao.dto.KakaoMemberResponse;
import odo.server.infra.oauth.kakao.dto.KakaoToken;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OauthMemberClient {
    private final KakaoApiClient kakaoApiClient;
    private final KakaoOauthConfig kakaoOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.KAKAO;
    }

    // (1) - 먼저 Auth Code를 통해서 AccessToken을 조회합니다.
    // (2) - AccessToken을 가지고 회원 정보를 받아옵니다.
    // (3) - 회원 정보를 OauthMember 객체로 변환합니다.
    @Override
    public OauthMember fetch(String authCode) {
        KakaoToken tokenInfo = kakaoApiClient.fetchToken(tokenRequestParams(authCode)); // (1)
        KakaoMemberResponse kakaoMemberResponse =
                kakaoApiClient.fetchMember("Bearer " + tokenInfo.accessToken());  // (2)
        return kakaoMemberResponse.toDomain();  // (3)
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoOauthConfig.clientId());
        params.add("redirect_uri", kakaoOauthConfig.redirectUri());
        params.add("code", authCode);
        params.add("client_secret", kakaoOauthConfig.clientSecret());
        return params;
    }
}
