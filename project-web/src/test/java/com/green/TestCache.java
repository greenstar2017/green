/**
 * 
 */
package com.green;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.green.cache.test.TestCacheKey;
import com.green.framework.cache.base.RedisDao;

/**
 * @author yuanhualiang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@WebAppConfiguration
public class TestCache {

	@Test
	public void test() {
		
		TestCacheKey iCacheKey = new TestCacheKey();
		if(RedisDao.exist(iCacheKey)) {
			System.out.println(RedisDao.get(iCacheKey, String.class));
		}else {
			RedisDao.set(iCacheKey, "test");
		}
	}
}
