package com.dwi.saas.zuul.config.zipkin;

import brave.Tracer;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * OkHttpClient集成Trace跟踪的自动化配置部分
 *
 * @author dwi
 * @date 2020-12-09 17:23
 */
@Configuration
@ConditionalOnProperty(name = "saas.zipkin.enabled", havingValue = "true")
public class OkHttpTraceAutoConfig {

    /**
     * 如果还未定义OkHttpClient的bean，则采用此OkHttpClient
     */
    @Bean
    @ConditionalOnMissingBean(OkHttpClient.class)
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool(20, 10L, TimeUnit.SECONDS))
                .build();
    }

    @Bean
    public OkhttpTraceInterceptor okhttpTraceInterceptor(Tracer tracer) {
        return new OkhttpTraceInterceptor(tracer);
    }

    @Bean
    public OkHttpTracePostProcessor okHttpTracePostProcessor(Tracer tracer) {
        return new OkHttpTracePostProcessor(okhttpTraceInterceptor(tracer));
    }
}
