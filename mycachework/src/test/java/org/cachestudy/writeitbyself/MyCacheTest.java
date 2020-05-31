package org.cachestudy.writeitbyself;

import org.cachestudy.writeitbyself.bean.User;
import org.cachestudy.writeitbyself.store.impl.BasicDataStore;
import org.cachestudy.writeitbyself.store.impl.LRUDataStore;
import org.cachestudy.writeitbyself.store.impl.WeakValueDataStore;
import org.junit.Test;

public class MyCacheTest {
	@Test
	public void TestStrongValue() throws InterruptedException {
		MyCache<String, User> cache = new MyCache<String, User>(new BasicDataStore<String, User>());
		String key = "19120480";
		User user = new User();
		user.setName("王一鸣");
		cache.put(key, user);
		user = null;
		System.out.println("Hello " + cache.get(key).getName());
		System.gc();
		Thread.sleep(1000);
		System.out.println("Hello " + cache.get(key));
	}

	@Test
	public void TestWeakValue() throws InterruptedException {
		MyCache<String, User> cache = new MyCache<String, User>(new WeakValueDataStore<String, User>());
		String key = "19120480";
		User user = new User();
		user.setName("王一鸣");
		cache.put(key, user);
		user = null;
		System.out.println("Hello " + cache.get(key).getName());
		System.gc();
		Thread.sleep(1000);
		System.out.println("Hello " + cache.get(key));
	}

	@Test
	public void TestLRU() {
		MyCache<String, User> cache = new MyCache<String, User>(new LRUDataStore<String, User>(3));
		String key = "19120480";
		User user = new User();
		user.setName("王一鸣");

		String key1 = "110";
		User user1 = new User();
		user1.setName("小李");

		String key2 = "120";
		User user2 = new User();
		user2.setName("小张");

		String key3 = "114";
		User user3 = new User();
		user3.setName("小白");
		
		cache.put(key, user);
		cache.put(key1, user1);
		cache.get(key);
		cache.put(key2, user2);
		cache.put(key3, user3);
		
		System.out.println("key=120的小张已经被remove,结果为"+cache.get(key1));
		
		if (cache.get(key) != null) {
			System.out.println("Hello " + cache.get(key).getName());
		}
	
		if (cache.get(key2) != null) {
			System.out.println("Hello " + cache.get(key2).getName());
		}
		if (cache.get(key3) != null) {
			System.out.println("Hello " + cache.get(key3).getName());
		}
	}
}
