package com.mycompany.main.lib.dtos;

import java.io.Serializable;

public class EmailConfigDTO implements Serializable{
    private String username;
    private String password;

    public EmailConfigDTO() {
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
    
    
}
