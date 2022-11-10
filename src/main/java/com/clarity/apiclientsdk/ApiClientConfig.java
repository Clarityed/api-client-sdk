package com.clarity.apiclientsdk;

import com.clarity.apiclientsdk.client.ClarityClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// 提示前缀
@ConfigurationProperties(prefix = "api.client")
@Data
// 组件包扫描注解
@ComponentScan
public class ApiClientConfig {

    /**
     * ak（相当于用户账号）
     */
    private String accessKey;

    /**
     * sk（相当于密钥）
     */
    private String secretKey;

    @Bean
    public ClarityClient clarityClient() {
        return new ClarityClient(accessKey, secretKey);
    }

}
