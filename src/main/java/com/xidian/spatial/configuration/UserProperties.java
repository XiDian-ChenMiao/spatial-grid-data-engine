package com.xidian.spatial.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件描述：用户配置
 * 创建作者：陈苗
 * 创建时间：2017/6/3 13:14
 */
@ConfigurationProperties(prefix = "user")
@Component
public class UserProperties {
    private String name;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
