package odo.server.application;

import lombok.RequiredArgsConstructor;
import odo.server.domain.OauthId;
import odo.server.domain.OauthMember;
import odo.server.domain.OauthMemberRepository;
import odo.server.domain.OauthServerType;
import odo.server.domain.authcode.AuthCodeRequestUrlProviderComposite;
import odo.server.domain.client.OauthMemberClientComposite;
import odo.server.post.Post;

import org.springframework.beans.factory.annotation.Value;
import odo.server.store.domain.UserPoint;
import odo.server.store.repository.UserPointRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
 
import jakarta.servlet.http.HttpSession;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;
    private final JdbcTemplate jdbcTemplate;
    @Value("${app.image.base-url}")
    private String baseUrl;
    private final UserPointRepository userPointRepository;

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
        String[] array = new String[4];
        //이메일 겨져옴
        array[0] = saved.email();
        //블로그 이름 가져옴
        array[1] = saved.blogName();

        // id 를 함께 넘겨주는 이유는 db 조회 시 id 를 사용하기 위함
        array[2] = String.valueOf(saved.id());

        // 사용자 닉네임 가져옴
        array[3] = saved.nickname();

        return array;
    }

    public String insertBlogInfo(Long id, String userBlogName, String userBlogAddress, String userBlogNickName) {
        String sql = "UPDATE oauth_member SET blog_name=?, blog_address=?, blog_nickname=? WHERE id=?";
        Object[] params = { userBlogName, userBlogAddress, userBlogNickName, id };
        jdbcTemplate.update(sql, params);

        UserPoint userPoint = new UserPoint();
        userPoint.setId(id);

        userPointRepository.save(userPoint);

        return "insert ok";
    }


    public Map<String, Object> selectByUserId(Long userId) {
        // String[] array = new String[3];
        String sql = "SELECT blog_nickname, email, blog_name, introduction, profile_image_url FROM oauth_member WHERE id=?";

        // Select One Row
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, userId);

        System.out.println("*********************************");
        System.out.println(result);
        return result;
    }
    public void saveProfileImg(String filename, Integer userId){
        String url = baseUrl + filename;
        String sql = "UPDATE oauth_member SET profile_image_url = ? WHERE id = ?";
        Object[] params = { url, userId };
        jdbcTemplate.update(sql, params);
    }
    public void equipIU(Integer userId) {
        String sql = "UPDATE oauth_member SET actor = ? WHERE id = ?";
        Object[] params = { "IU", userId };
        jdbcTemplate.update(sql, params);

    }


}
