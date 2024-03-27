package odo.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogInfoOauthMember {
    private String email;
    private Long id;
    private String userBlogName;
    private String userBlogNickname;
    private String userBlogAddress;

    public String getEmail(){
        return email;
    }
    public Long getId(){
        return id;
    }

    public String getUserBlogName(){
        return userBlogName;
    }

    public String getUserBlogAddress(){
        return userBlogAddress;
    }

    public String getUserBlogNickname(){
        return userBlogNickname;
    }
}
 