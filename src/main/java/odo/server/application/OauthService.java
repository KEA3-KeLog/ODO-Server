package odo.server.application;

import lombok.RequiredArgsConstructor;
import odo.server.domain.OauthId;
import odo.server.domain.OauthMember;
import odo.server.domain.OauthMemberRepository;
import odo.server.domain.OauthServerType;
import odo.server.domain.authcode.AuthCodeRequestUrlProviderComposite;
import odo.server.domain.client.OauthMemberClientComposite;
import odo.server.post.Post;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public String insertBlogInfo(Long id, String userBlogName, String userBlogAddress, String userBlogNickName) {
        String sql = "UPDATE oauth_member SET blog_name=?, blog_address=?, blog_nickname=? WHERE id=?";
        Object[] params = { userBlogName, userBlogAddress, userBlogNickName, id };
        jdbcTemplate.update(sql, params);

        return "insert ok";
    }

    // db에서 userId 를 기준으로 db를 조회하여 정보를 가져옵니다.
    public Map<String, Object> selectByUserId(Long userId) {
        // String[] array = new String[3];
        String sql = "SELECT blog_nickname, email, blog_name FROM oauth_member WHERE id=?";
        // String sql = "SELECT blog_nickname,email, profile_img_url FROM oauth_member
        // WHERE id=?";

        // Map<String, Object> params = new HashMap<>();
        // params.put(userId);

        // namedParameterJdbcTemplate.queryForList(sql, params);

        // Select One Row
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, userId);

        // String[] array = new String[3];
        // array[0] = saved.blogNickname();
        // array[1] = saved.profileImageUrl();
        // array[2] = saved.email();

        System.out.println("*********************************");
        System.out.println(result);
        return result;
    }

}
