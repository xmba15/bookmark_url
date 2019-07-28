package com.bookmarkapp.bookmark_url.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "webjars")
@Data
public class WebjarsConfig {
    private String bootstrap;
    private String jquery;
}
