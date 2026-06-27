package com.itfd.pojo;

import java.util.Objects;

/**
 * 登录响应数据实体类
 */
public class Login {
    private Integer id;
    private String username;
    private String name;
    private String token;

    public Login() {
    }

    public Login(Integer id, String username, String name, String token) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.token = token;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return Objects.equals(id, login.id) && Objects.equals(username, login.username) && Objects.equals(name, login.name) && Objects.equals(token, login.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, name, token);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
