package org.cachestudy.writeitbyself;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

import org.cachestudy.writeitbyself.bean.User;
import org.junit.Test;

public class CsCacheTest {
	@Test
	public void test01() {
		CachingProvider cachingProvider = Caching.getCachingProvider();
		CacheManager manager = cachingProvider.getCacheManager();
		//返回的cache类型是<String, User, Configuration<String, User>>,也可以不加
		Cache<String, User> cache = (Cache<String, User>) manager
				.<String, User, Configuration<String, User>> createCache("Test",
						new MutableConfiguration<String, User>());
		String key = "19120480";
		User user = new User();
		user.setName("王一鸣");
		cache.put(key, user);
		System.out.println("Hello " + cache.get(key).getName());

	}
}
