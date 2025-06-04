package com.virtual.threads.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * package com.virtual.threads.config; /**
 *
 * @author <John Brix Pomoy>
 * @version $Id: RestClientConfig.java, v 0.1 2025-06-04 8:48 PM John Brix Pomoy Exp $$
 */
@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient(){
        return RestClient.builder().build();
    }
}
