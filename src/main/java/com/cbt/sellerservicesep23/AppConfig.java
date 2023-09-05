package com.cbt.sellerservicesep23;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

public class AppConfig
{
    @Bean
    public WebClient.Builder loadBalancedWebClientBuilder()
    {
        return WebClient.builder();
    }

}
