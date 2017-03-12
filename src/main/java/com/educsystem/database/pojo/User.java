package com.educsystem.database.pojo;

/**
 * Created by Denis on 23.02.2017.
 */
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private int level;
    private String role;

    public User(int id, String username, String email, String password, int level, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.level = level;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
