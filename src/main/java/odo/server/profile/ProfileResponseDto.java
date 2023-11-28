package odo.server.profile;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDto {
    private String blogName;
    private String blogNickName;
    private String introduction;
    private String userEmail;
    private String sociala;
    private String socialb;
    private String socialc;
    private String sociald;
    private boolean reviewReceived;
    private boolean updateReceived;
}
