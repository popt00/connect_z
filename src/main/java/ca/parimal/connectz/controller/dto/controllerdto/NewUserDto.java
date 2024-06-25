package ca.parimal.connectz.controller.dto.controllerdto;

import java.util.List;
public class NewUserDto {
    private String username;
    private String password;
    private List<String> authorities;

    public  NewUserDto(List<String> authorities) {
        this.authorities = authorities;
    }
    public  NewUserDto() {

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
