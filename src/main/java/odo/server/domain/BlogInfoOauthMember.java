package odo.server.domain;

public class BlogInfoOauthMember {
    private String email;
    private Long id;
    private String userBlogName;
    private String userBlogNickname;
    private String userBlogAddress;

    public BlogInfoOauthMember(String email, Long id, String userBlogName, String userBlogNickname, String userBlogAddress) {
        this.email = email;
        this.id = id;
        this.userBlogName = userBlogName;
        this.userBlogNickname = userBlogNickname;
        this.userBlogAddress = userBlogAddress;
    }

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
