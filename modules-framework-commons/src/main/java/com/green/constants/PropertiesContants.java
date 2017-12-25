package com.green.constants;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置常量
 * 
 * @author yuanhualiang
 */
@Component
@ConfigurationProperties(prefix = "")
public class PropertiesContants {

	public static PropertiesContants that;

	@PostConstruct
	private void init() {
		that = this;
	}

	/**
	 * cookie存储域名
	 */
	private String cookieBaseDomain = "";

	public String getCookieBaseDomain() {
		return cookieBaseDomain;
	}

	public void setCookieBaseDomain(String cookieBaseDomain) {
		this.cookieBaseDomain = cookieBaseDomain;
	}
}
