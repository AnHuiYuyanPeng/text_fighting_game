package com.fighting.domain;

public class User {
    // 用户名
    private String username;
    // 密码
    private String password;
    // 用户ID
    private int id;
    // 用户状态 使用枚举类来表示    
    private UserStatus status;

    public User() {}

    public User(String username, String password, int id, UserStatus status) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.status = status;
    }

    // 自动生成id
    public static int generateId() {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append((int) (Math.random() * 10));
        }
        return Integer.parseInt(id.toString());
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status.getStatus();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
