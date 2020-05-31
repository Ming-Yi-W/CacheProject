package org.cachestudy.writeitbyself;

import org.cachestudy.writeitbyself.bean.User;
import org.cachestudy.writeitbyself.store.impl.LRUDataStore;

public class Mytest {
	public static void main(String[] args) {
		MyCache<String, User> cache = new MyCache<String, User>(new LRUDataStore<String, User>(2));
		String key = "leo";
		User user = new User();
		user.setName("leo");

		
		String key1 = "liu";
		User user1 = new User();
		user1.setName("liu");

		String key2 = "robin";
		User user2 = new User();
		user2.setName("robin");

		cache.put(key, user);
		cache.put(key1, user1);
		System.out.println(cache.get(key1));
		cache.put(key2, user2);

		if (cache.get(key) != null) {
			System.out.println("Hello " + cache.get(key).getName());
		}
		if (cache.get(key1) != null) {
			System.out.println("Hello " + cache.get(key1).getName());
		}
		if (cache.get(key2) != null) {
			System.out.println("Hello " + cache.get(key2).getName());
		}
		
		User oldUser = cache.remove(key1);
		System.out.println(oldUser);
		
	}
}
