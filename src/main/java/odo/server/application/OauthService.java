package odo.server.application;

import lombok.RequiredArgsConstructor;
import odo.server.domain.OauthId;
import odo.server.domain.OauthMember;
import odo.server.domain.OauthMemberRepository;
import odo.server.domain.OauthServerType;
import odo.server.domain.authcode.AuthCodeRequestUrlProviderComposite;
import odo.server.domain.client.OauthMemberClientComposite;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;
    private final JdbcTemplate jdbcTemplate;

    // OauthServerType을 받아서 해당 인증 서버에서 Auth Code를 받아오기 위한 URL 주소를 생성해줍니다.
    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    // 받아온 정보를 통해 로그인을 진행한다.
    // 이때 회원가입 되어있지 않다면 회원가입도 함께 진행한다.
    // JWT를 사용한다면 여기서 JWT로 AccessToken을 생성하여 반환하여야 합니다.
    public String[] login(OauthServerType oauthServerType, String authCode) {

        // 먼저 로그인을 진행하려는 OauthServerType에 해당하는 회원을 AuthCode를 통해 조회합니다.
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);

        // 그리고 유니크함이 보장되는 OauthId를 통해 데이터베이스에서 회원을 찾아옵니다.
        // 만약 가입되어있지 않다면 저장(회원가입)합니다.
        OauthMember saved = oauthMemberRepository.findByOauthId(oauthMember.oauthId())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember));
        String[] array = new String[3];
        array[0] = saved.email();
        array[1] = saved.blogName();
        // id 를 함께 넘겨주는 이유는 db 조회 시 id 를 사용하기 위함
        array[2] = String.valueOf(saved.id());

        return array;
    }

    // 프론트엔드에서 받아온 추가 정보를 OauthMember에 입력합니다.
//    public String insertBlogInfo(Long id, String userBlogName, String userBlogAddress, String userBlogNickName) {
//        String sql = "UPDATE oauth_member SET blog_name=?, blog_address=?, blog_nickname=? WHERE id=?";
//        Object[] params = {userBlogName, userBlogAddress, userBlogNickName, id};
//        jdbcTemplate.update(sql, params);
//        System.out.println("insert Called");
//
//
//        return "insert Called";
//    }

    // 변경사항
    public String insertBlogInfo(Long id, String userBlogName, String userBlogAddress, String userBlogNickName) {
//        // db.odo_user.update({조건})
//        Criteria criteria = new Criteria("id");
//        criteria.is(id);
//
//        Query query = new Query();
//        // 업데이트 할 항목 정의
//        Update update = new Update();
//        update.set("blog_name", userBlogName);
//        update.set("blog_address", userBlogAddress);
//        update.set("blog_nickname", userBlogNickName);

        String sql = "UPDATE oauth_member SET blog_name=?, blog_address=?, blog_nickname=? WHERE id=?";
        Object[] params = {userBlogName, userBlogAddress, userBlogNickName, id};
        jdbcTemplate.update(sql, params);
        System.out.println("insert Called");


        return "insert Called";
    }
}
