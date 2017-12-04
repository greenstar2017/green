/**
 * 
 */
package com.green.cache.test;

import java.util.concurrent.TimeUnit;

import com.green.cache.base.ProjectICacheKey;

/**
 * @author yuanhualiang
 *
 */
public class TestCacheKey extends ProjectICacheKey {

	@Override
	public String getSuffixKey() {
		return "";
	}

	@Override
	public int getExpirationTime() {
		return 1;
	}

	@Override
	public TimeUnit getExpirationTimeUnit() {
		return TimeUnit.MINUTES;
	}


}
