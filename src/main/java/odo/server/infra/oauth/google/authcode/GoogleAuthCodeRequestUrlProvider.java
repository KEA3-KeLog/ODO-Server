package odo.server.infra.oauth.google.authcode;

import lombok.RequiredArgsConstructor;
import odo.server.domain.OauthServerType;
import odo.server.domain.authcode.AuthCodeRequestUrlProvider;
import odo.server.infra.oauth.google.GoogleOauthConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class GoogleAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {
    private final GoogleOauthConfig googleOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.GOOGLE;
    }

    @Override
    public String provide() {
        return UriComponentsBuilder
                .fromUriString("https://accounts.google.com/o/oauth2/v2/auth")
                .queryParam("response_type", "code")
                .queryParam("client_id", googleOauthConfig.clientId())
                .queryParam("redirect_uri", googleOauthConfig.redirectUri())
                .queryParam("state", "samplestate") // 이건 나중에 따로 찾아보고 설정해서 쓰세용!
                .build()
                .toUriString();
    }
}
