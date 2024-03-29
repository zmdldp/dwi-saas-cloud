package com.dwi.saas.authority.service.common.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.dwi.basic.base.service.SuperServiceImpl;
import com.dwi.basic.cache.model.CacheKey;
import com.dwi.basic.cache.repository.CacheOps;
import com.dwi.basic.utils.CollHelper;
import com.dwi.basic.utils.DateUtils;
import com.dwi.saas.authority.dao.common.LoginLogMapper;
import com.dwi.saas.authority.domain.entity.auth.User;
import com.dwi.saas.authority.domain.entity.common.LoginLog;
import com.dwi.saas.authority.service.auth.UserService;
import com.dwi.saas.authority.service.common.LoginLogService;
import com.dwi.saas.common.cache.common.LoginLogBrowserCacheKeyBuilder;
import com.dwi.saas.common.cache.common.LoginLogSystemCacheKeyBuilder;
import com.dwi.saas.common.cache.common.LoginLogTenDayCacheKeyBuilder;
import com.dwi.saas.common.cache.common.TodayLoginIvCacheKeyBuilder;
import com.dwi.saas.common.cache.common.TodayLoginPvCacheKeyBuilder;
import com.dwi.saas.common.cache.common.TodayPvCacheKeyBuilder;
import com.dwi.saas.common.cache.common.TotalLoginIvCacheKeyBuilder;
import com.dwi.saas.common.cache.common.TotalLoginPvCacheKeyBuilder;
import com.dwi.saas.common.cache.common.TotalPvCacheKeyBuilder;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 业务实现类
 * 系统日志
 * </p>
 *
 * @author dwi
 * @date 2020-10-20
 */
@Slf4j
@Service
@DS("#thread.tenant")
@RequiredArgsConstructor
public class LoginLogServiceImpl extends SuperServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {
    private final UserService userService;
    private final CacheOps cacheOps;

    private static final Supplier<Stream<String>> BROWSER = () -> Stream.of(
            "Chrome", "Firefox", "Microsoft Edge", "Safari", "Opera"
    );
    private static final Supplier<Stream<String>> OPERATING_SYSTEM = () -> Stream.of(
            "Android", "Linux", "Mac OS X", "Ubuntu", "Windows 10", "Windows 8", "Windows 7", "Windows XP", "Windows Vista"
    );

    private static String simplifyOperatingSystem(String operatingSystem) {
        return OPERATING_SYSTEM.get().parallel().filter(b -> StrUtil.containsIgnoreCase(operatingSystem, b)).findAny().orElse(operatingSystem);
    }

    private static String simplifyBrowser(String browser) {
        return BROWSER.get().parallel().filter(b -> StrUtil.containsIgnoreCase(browser, b)).findAny().orElse(browser);
    }

    @Override
    //public LoginLog save(Long userId, String account, String ua, String ip, String location, String description) {
    public boolean save(LoginLog loginLogDto) {  
    	User user;
        if (loginLogDto.getUserId() != null) {
            user = this.userService.getByIdCache(loginLogDto.getUserId());
        } else {
            user = this.userService.getByAccount(loginLogDto.getAccount());
        }

        LoginLog loginLog = LoginLog.builder()
                .location(loginLogDto.getLocation())
                .loginDate(DateUtils.formatAsDate(LocalDateTime.now()))
                .description(loginLogDto.getDescription())
                .requestIp(loginLogDto.getRequestIp())
                .ua(loginLogDto.getUa())
                .build();

        UserAgent userAgent = UserAgent.parseUserAgentString(loginLogDto.getUa());
        Browser browser = userAgent.getBrowser();
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        Version browserVersion = userAgent.getBrowserVersion();
        if (browser != null) {
            loginLog.setBrowser(simplifyBrowser(browser.getName()));
        }
        if (browserVersion != null) {
            loginLog.setBrowserVersion(browserVersion.getVersion());
        }
        if (operatingSystem != null) {
            loginLog.setOperatingSystem(simplifyOperatingSystem(operatingSystem.getName()));
        }
        if (user != null) {
            loginLog.setAccount(user.getAccount()).setUserId(user.getId()).setUserName(user.getName())
                    .setCreatedBy(user.getId());
        }

        super.save(loginLog);
        LocalDate now = LocalDate.now();
        String tenDaysAgo = DateUtils.formatAsDate(now.plusDays(-9));

        CacheKey totalLoginPvKey = TotalLoginPvCacheKeyBuilder.build();
        CacheKey todayLoginPvKey = TodayLoginPvCacheKeyBuilder.build(now);
        // 登录IV
        CacheKey totalLoginIvKey = TotalLoginIvCacheKeyBuilder.build();
        CacheKey todayLoginIvKey = TodayLoginIvCacheKeyBuilder.build(now);

        CacheKey loginLogTenDayKey = new LoginLogTenDayCacheKeyBuilder().key(tenDaysAgo);
        CacheKey loginLogBrowserKey = new LoginLogBrowserCacheKeyBuilder().key();
        CacheKey loginLogSystemKey = new LoginLogSystemCacheKeyBuilder().key();
        cacheOps.del(totalLoginPvKey);
        cacheOps.del(todayLoginPvKey);
        cacheOps.del(todayLoginIvKey);
        cacheOps.del(totalLoginIvKey);
        cacheOps.del(loginLogTenDayKey);
        cacheOps.del(loginLogBrowserKey);
        cacheOps.del(loginLogSystemKey);
        if (user != null) {
            CacheKey loginLogTenDayUserKey = new LoginLogTenDayCacheKeyBuilder().key(tenDaysAgo, user.getAccount());
            cacheOps.del(loginLogTenDayUserKey);
        }
        return true; //loginLog
    }

    @Override
    public void pvIncr() {
        CacheKey totalPvKey = TotalPvCacheKeyBuilder.build();
        cacheOps.incr(totalPvKey);

        CacheKey todayPvKey = TodayPvCacheKeyBuilder.build(LocalDate.now());
        cacheOps.incr(todayPvKey);
    }

    @Override
    public Long getTotalPv() {
        CacheKey key = TotalPvCacheKeyBuilder.build();
        return Convert.toLong(cacheOps.get(key, k -> 0L), 0L);
    }

    @Override
    public Long getTodayPv() {
        CacheKey key = TodayPvCacheKeyBuilder.build(LocalDate.now());
        return Convert.toLong(cacheOps.get(key, k -> 0L), 0L);
    }

    @Override
    public Long getTotalLoginPv() {
        CacheKey loginLogTotalKey = TotalLoginPvCacheKeyBuilder.build();
        return Convert.toLong(cacheOps.get(loginLogTotalKey, key -> this.baseMapper.getTotalLoginPv()), 0L);
    }

    @Override
    public Long getTodayLoginPv() {
        LocalDate now = LocalDate.now();
        CacheKey loginLogTodayKey = TodayLoginPvCacheKeyBuilder.build(now);
        return Convert.toLong(cacheOps.get(loginLogTodayKey, k -> this.baseMapper.getTodayLoginPv(now)), 0L);
    }

    @Override
    public Long getTotalLoginIv() {
        CacheKey key = TotalLoginIvCacheKeyBuilder.build();
        return Convert.toLong(cacheOps.get(key, k -> this.baseMapper.getTotalLoginIv()), 0L);
    }

    @Override
    public Long getTodayLoginIv() {
        LocalDate now = LocalDate.now();
        CacheKey loginLogTodayIpKey = TodayLoginIvCacheKeyBuilder.build(now);
        return Convert.toLong(cacheOps.get(loginLogTodayIpKey, k -> this.baseMapper.getTodayLoginIv(now)), 0L);
    }

    @Override
    public List<Map<String, String>> findLastTenDaysVisitCount(String account) {
        LocalDateTime tenDaysAgo = LocalDateTime.of(LocalDate.now().plusDays(-9), LocalTime.MIN);
        String tenDaysAgoStr = DateUtils.formatAsDate(tenDaysAgo);
        CacheKey loginLogTenDayKey = new LoginLogTenDayCacheKeyBuilder().key(tenDaysAgoStr, account);
        return cacheOps.get(loginLogTenDayKey, k -> {
            List<Map<String, String>> map = baseMapper.findLastTenDaysVisitCount(tenDaysAgo, account);
            return map.stream().map(item -> {
                Map<String, String> kv = new HashMap<>(CollHelper.initialCapacity(map.size()));
                kv.put("login_date", item.get("login_date"));
                kv.put("count", String.valueOf(item.get("count")));
                return kv;
            }).collect(Collectors.toList());
        });
    }

    @Override
    public List<Map<String, Object>> findByBrowser() {
        CacheKey loginLogBrowserKey = new LoginLogBrowserCacheKeyBuilder().key();
        return cacheOps.get(loginLogBrowserKey, k -> baseMapper.findByBrowser());
    }

    @Override
    public List<Map<String, Object>> findByOperatingSystem() {
        CacheKey loginLogSystemKey = new LoginLogSystemCacheKeyBuilder().key();
        return cacheOps.get(loginLogSystemKey, k -> baseMapper.findByOperatingSystem());
    }

    @Override
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        return baseMapper.clearLog(clearBeforeTime, clearBeforeNum) > 0;
    }
}
