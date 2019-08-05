package com.yangm.config;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Ehcache配置
 */
@Configuration
public class EhCacheConfig {

	@SuppressWarnings("unused")
	@Bean("ehCacheManager")
	public EhCacheManager cacheManager() {
		EhCacheManager cacheManager = new EhCacheManager();
		if (cacheManager == null) {
			cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
		}

		return cacheManager;
	}
}
