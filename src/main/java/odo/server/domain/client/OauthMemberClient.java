package odo.server.domain.client;

import odo.server.domain.OauthMember;
import odo.server.domain.OauthServerType;

// Auth Code를 통해 최종적으로 OauthMember를 생성하는 로직을 생성하기 위해
// OauthMemberClient라는 인터페이스를 정의
public interface OauthMemberClient {

    // server type을 정의해 줍니다.
    OauthServerType supportServer();

    // fetch() 메서드를 통해 OauthMember를 반환
    // fetch() 메서드는 인자로 Auth Code를 받습니다.
    OauthMember fetch(String code);
}

//    회원가입 혹은 로그인 할 회원 정보를 받아오는 과정을 나열하면 다음과 같습니다.
//
//        Auth Code를 통해 AccessToken 발급
//        AccessToken을 통해 회원 정보 조회
//
//     이 과정을 Auth Code를 통해 회원 정보를 조회하는 과정으로 캡슐화하였습니다.
 