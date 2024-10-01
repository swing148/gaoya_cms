package com.xxgc.config;
import com.xxgc.dialect.DictDialect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 注册方言
 */
@Configuration
public class DialectConfig {
    @Bean
    @ConditionalOnMissingBean
    public DictDialect customDialect() {
        return new DictDialect("自定义的字典方言类");

    }
}