package com.dwi.saas.zuul.config;

import com.alibaba.fastjson.JSONArray;
import com.dwi.basic.swagger2.properties.SwaggerProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 资源配置
 *
 * @author dwi
 * @date 2020-07-25 22:13
 */
@Component
@Primary
@Slf4j
@RequiredArgsConstructor
public class SwaggerResourceConfig implements SwaggerResourcesProvider {
    private final RouteLocator routeLocator;
    private final SwaggerProperties swaggerProperties;
    @Resource(name = "lbRestTemplate")
    RestTemplate restTemplate;
    @Value("${server.servlet.context-path:/api}")
    private String contextPath;

    /**
     * 每次访问 http://localhost:8670/api/gate/doc.html 会调用该方法
     *
     * @return
     */
    @Override
    public List<SwaggerResource> get() {
        String url = "/swagger-resources";
        //获取所有router
        List<SwaggerResource> resources = new ArrayList<>();
        List<Route> routes = routeLocator.getRoutes();
        log.info("Route Size:{}", routes.size());
        Set<String> services = new HashSet<>();
        for (Route route : routes) {
            if (services.contains(route.getLocation())) {
                continue;
            }
            try {
                // 通过 restTemplate 远程调用各个服务的 /swagger-resources 地址。获取各个服务的swagger配置
                // 访问地址： http://saas-test-server/swagger-resources  （其中 saas-test-server 必须是服务的注册到nacos中的名称，否则无法调用成功 ）
                JSONArray list = restTemplate.getForObject("http://" + route.getLocation() + url, JSONArray.class);
                if (list != null && !list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        SwaggerResource sr = list.getObject(i, SwaggerResource.class);
                        resources.add(swaggerResource(route.getId() + "-" + sr.getName(), contextPath + route.getPrefix() + sr.getUrl()));
                    }
                }
                services.add(route.getLocation());
            } catch (Exception e) {
                log.info(route.getLocation() + "服务尚未启动,无法获取swagger信息");
            }
        }

        if (swaggerProperties != null) {
            resources.add(swaggerResource(swaggerProperties.getTitle(), "/v2/api-docs?group=" + swaggerProperties.getTitle()));
        }

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        log.info("name:{},location:{}", name, location);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
