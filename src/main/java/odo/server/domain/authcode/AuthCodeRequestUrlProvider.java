package odo.server.domain.authcode;

import odo.server.domain.OauthServerType;

public interface AuthCodeRequestUrlProvider {

    // KAKAO 라면 KAKAO 반환.. NAVER라면 NAVER 반환..
    OauthServerType supportServer();

    // 인가코드를 받기위한 URL 로 리디렉트
    String provide();
}
