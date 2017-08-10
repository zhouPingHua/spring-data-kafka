package com.caiyi.financial.data.config;

import com.caiyi.financial.data.kafka.KafkaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by zph  Date：2017/8/9.
 */
@Configuration
public class Config {

    @Bean("KafkaService")
    @Scope("singleton")
    public KafkaService getStormLocalRpcHandle(){
        return  KafkaService.create();
    }
}
