package com.dwi.saas.gateway.biz.config;

import com.dwi.basic.boot.config.BaseConfig;
import com.dwi.basic.jwt.TokenUtil;
import com.dwi.saas.common.properties.IgnoreProperties;
import com.dwi.saas.gateway.biz.filter.gateway.PreCheckFilter;
//import com.dwi.saas.gateway.filter.zuul.ZuulMvcConfigurer;
//import com.dwi.saas.gateway.filter.zuul.ZuulPreCheckFilter;
import com.dwi.saas.gateway.biz.service.BlockListService;
import com.dwi.saas.gateway.biz.service.RateLimiterService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dwi
 * @date 2017-12-15 14:42
 */
@Configuration
public class GatewayWebConfiguration extends BaseConfig {

    /**
     * saas-zuul-server 服务在配置文件中配置 saas.webmvc.enabled=true ，会加载下面2个Bean
     */
//    @ConditionalOnProperty(prefix = "saas.webmvc", name = "enabled", havingValue = "true", matchIfMissing = true)
//    public static class WebmvcConfig {
//        /**
//         * zuul服务 限流 + 阻止访问 功能的过滤器
//         *
//         * @param blockListService   阻止列表Service （spring自动注入）
//         * @param rateLimiterService 限流Service （spring自动注入）
//         */
//        @Bean
//        public ZuulPreCheckFilter getZuulPreCheckFilter(BlockListService blockListService, RateLimiterService rateLimiterService) {
//            return new ZuulPreCheckFilter(blockListService, rateLimiterService);
//        }
//
//        @Bean
//        public ZuulMvcConfigurer getZuulMvcConfigurer(TokenUtil tokenUtil, @Value("${saas.database.multiTenantType:SCHEMA}") String multiTenantType,
//                                                      IgnoreProperties ignoreTokenProperties) {
//            return new ZuulMvcConfigurer(tokenUtil, multiTenantType, ignoreTokenProperties);
//        }
//
//    }

    /**
     * saas-gateway-server 服务在配置文件中配置 saas.webmvc.enabled=false ，会加载下面1个Bean
     */
    @ConditionalOnProperty(prefix = "saas.webmvc", name = "enabled", havingValue = "false", matchIfMissing = true)
    public static class WebfluxConfig {
        /**
         * gateway服务 限流 + 阻止访问 功能的过滤器
         *
         * @param blockListService   阻止列表Service （spring自动注入）
         * @param rateLimiterService 限流Service （spring自动注入）
         */
        @Bean
        public PreCheckFilter getPreCheckFilter(BlockListService blockListService, RateLimiterService rateLimiterService) {
            return new PreCheckFilter(blockListService, rateLimiterService);
        }
    }
}
