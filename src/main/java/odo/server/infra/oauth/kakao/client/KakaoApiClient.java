package odo.server.infra.oauth.kakao.client;

import odo.server.infra.oauth.kakao.dto.KakaoMemberResponse;
import odo.server.infra.oauth.kakao.dto.KakaoToken;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

public interface KakaoApiClient {

    // Access token 을 받아오기 위한 URL 입니다.
    // contentType 은 Access token 요청 시 필요한 요청 헤더 입니다.
    // 응답 값은 KakaoToken을 통해 객체로 바로 받아올 수 있습니다.
    @PostExchange(url = "https://kauth.kakao.com/oauth/token", contentType = APPLICATION_FORM_URLENCODED_VALUE)
    KakaoToken fetchToken(@RequestParam(name="params") MultiValueMap<String, String> params);

    //  AccessToken을 통해 사용자 정보를 받아와 OauthMember를 생성
    //  AccessToken을 통해 회원 정보를 조회하는 기능입니다.
    @GetExchange("https://kapi.kakao.com/v2/user/me")
    KakaoMemberResponse fetchMember(@RequestHeader(name = AUTHORIZATION) String bearerToken);
}
 