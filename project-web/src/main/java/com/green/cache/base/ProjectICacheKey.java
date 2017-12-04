package com.green.cache.base;

import com.green.framework.cache.key.ICacheKey;

/**
 * 简莎工程缓存KEY项目名称
 * 
 * @author yuanhualiang
 *
 */
public abstract class ProjectICacheKey extends ICacheKey {

	public ProjectICacheKey() {
	}

	@Override
	public String getProjectPrefixKey() {
		return PROJECT;
	}

	public static final String PROJECT = "project";
}