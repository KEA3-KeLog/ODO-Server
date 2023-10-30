package odo.server.infra.oauth.google.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import odo.server.domain.OauthId;
import odo.server.domain.OauthMember;

import static odo.server.domain.OauthServerType.GOOGLE;

@JsonNaming(value = SnakeCaseStrategy.class)
public record GoogleMemberResponse(
        String id,
        String name,
        String email,
        String picture
) {

    public OauthMember toDomain() {
        return OauthMember.builder()
                .oauthId(new OauthId(id, GOOGLE))
                .nickname(name)
                .profileImageUrl(picture)
                .email(email)
                .build();
    }

}
