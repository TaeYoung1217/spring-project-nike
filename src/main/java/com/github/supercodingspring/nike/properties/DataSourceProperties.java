package com.github.supercodingspring.nike.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "datasource1")
public class DataSourceProperties {
    private String username;
    private String password;
    private String url;
    private String driverClassName;
}