package odo.server.profile;

import odo.server.domain.OauthMember;
import odo.server.domain.OauthMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
 
@RestController
public class ProfileController {
    @Autowired
    OauthMemberRepository oauthMemberRepository;

    @GetMapping("/profile/api/userprofile/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable Long userId){
        Optional<OauthMember> member = oauthMemberRepository.findById(userId);

        if(member.isPresent()){
            OauthMember oauthMember = member.get();

            ProfileResponseDto profileResponseDto = ProfileResponseDto.builder()
                    .blogName(oauthMember.getBlogName())
                    .blogNickName(oauthMember.getBlogNickname())
                    .introduction(oauthMember.getIntroduction())
                    .sociala(oauthMember.getSocialA())
                    .socialb(oauthMember.getSocialB())
                    .socialc(oauthMember.getSocialC())
                    .sociald(oauthMember.getSocialD())
                    .userEmail(oauthMember.getEmail())
                    .profileImageUrl(oauthMember.getProfileImageUrl())
                    .reviewReceived(oauthMember.isReviewReceived())
                    .updateReceived(oauthMember.isUpdateReceived())
                    .build();

            return ResponseEntity.ok(profileResponseDto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/profile/api/profileupdate/{userId}")
    public ResponseEntity<String> updateProfile(@PathVariable Long userId , @RequestBody ProfileResponseDto profileResponseDto){
        System.out.println("userId = " + userId);
        System.out.println("profileResponseDtoBlogName = " + profileResponseDto.getBlogName());
        System.out.println("profileResponseDto.getBlogNickName() = " + profileResponseDto.getBlogNickName());
        System.out.println("profileResponseDto.getIntroduction() = " + profileResponseDto.getIntroduction());
        System.out.println("profileResponseDto.getSocialb() = " + profileResponseDto.getSocialb());
        System.out.println("profileResponseDto.getUserEmail() = " + profileResponseDto.getUserEmail());


        Optional<OauthMember> member = oauthMemberRepository.findById(userId);

        if(member.isPresent()){
            OauthMember oauthMember = member.get();

            oauthMember.setBlogName(profileResponseDto.getBlogName());
            oauthMember.setBlogNickname(profileResponseDto.getBlogNickName());
            oauthMember.setIntroduction(profileResponseDto.getIntroduction());
            oauthMember.setSocialA(profileResponseDto.getSociala());
            oauthMember.setSocialB(profileResponseDto.getSocialb());
            oauthMember.setSocialC(profileResponseDto.getSocialc());
            oauthMember.setSocialD(profileResponseDto.getSociald());
            oauthMember.setEmail(profileResponseDto.getUserEmail());
            oauthMember.setReviewReceived(profileResponseDto.isReviewReceived());
            oauthMember.setUpdateReceived(profileResponseDto.isUpdateReceived());

            oauthMemberRepository.save(oauthMember);

            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error updating user: ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
