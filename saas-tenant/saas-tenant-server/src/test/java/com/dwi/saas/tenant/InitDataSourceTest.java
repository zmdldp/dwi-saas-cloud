package com.dwi.saas.tenant;

import java.text.ParseException;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.druid.pool.ha.PropertiesUtils;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.ListView;
import com.alibaba.spring.util.PropertySourcesUtils;
import com.dwi.saas.TenantServerApplication;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TenantServerApplication.class)
//@WebAppConfiguration
@Slf4j
@EnableConfigurationProperties({NacosDiscoveryProperties.class})
public class InitDataSourceTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @MockBean
    private RestTemplate restTemplate;
    
    @Autowired
    private NacosDiscoveryProperties nacosProperties;


    // @Before
    // public void contextLoads() {
    // mvc = MockMvcBuilders.webAppContextSetup(context).build();
    // }

    private static final Logger log = LoggerFactory.getLogger(InitDataSourceTest.class);
    public static final String PREFIX = "spring.cloud.nacos.discovery";
	private static final Pattern PATTERN = Pattern.compile("-(\\w)");
    
	@Autowired
	private Environment environment;

    @Test
    public void test() throws NacosException {
    	
    	Properties properties = new Properties();
    	//PropertiesConfiguration.Utils.filterPrefix(properties, PREFIX);
    	Map<String, Object> nacosPropertiesTest = PropertySourcesUtils
				.getSubProperties((ConfigurableEnvironment) environment, PREFIX);
    	//nacosProperties.
    	BeanUtil.beanToMap(nacosProperties).forEach((k, v) -> properties.putIfAbsent(k,
				String.valueOf(v)));
    	
    	
    	//properties = BeanUtil.beanToMap(nacosProperties);
    	//properties.
    	//		BeanUtil.beanToMap(nacosProperties, properties, false, true);
    	NamingService namingService = NacosFactory.createNamingService(properties);
    	ListView<String> services = namingService.getServicesOfServer(1, 100, nacosProperties.getGroup());
    	log.info("services:{}", services.getData());
    }
    
    private String resolveKey(String key) {
		Matcher matcher = PATTERN.matcher(key);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

}
