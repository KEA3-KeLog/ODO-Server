package odo.server.domain;

import odo.server.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OauthMemberRepository extends JpaRepository<OauthMember, Long> {
    Optional<OauthMember> findByOauthId(OauthId oauthId);
    Optional<OauthMember> findById(Long id);
}
 