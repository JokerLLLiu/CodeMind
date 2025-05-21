package com.software.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "codemind.jwt")
@Data
public class JwtProperties {
    /**
     * 管理端
     */
    private String secretKey;
    private Long ttl;
    private String tokenName;
}
