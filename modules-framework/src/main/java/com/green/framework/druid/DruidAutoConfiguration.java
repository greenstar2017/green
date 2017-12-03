package com.green.framework.druid;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 加载数据源
 * 
 * @author yuanhualiang
 *
 */
@Configuration
@EnableConfigurationProperties({ DruidProperties.class })
@ConditionalOnClass({ DruidDataSource.class })
public class DruidAutoConfiguration {

	@Autowired
	private DruidProperties druidProperties;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DruidAutoConfiguration.class);

	@Bean
	@ConditionalOnMissingBean({ DataSource.class })
	public DataSource dataSource() {
		LOGGER.info("auto configuration ==================> com.green.framework.druid datasource");

		DruidDataSource dataSource = new DruidDataSource();
		BeanUtils.copyProperties(this.druidProperties, dataSource);
		return dataSource;
	}
}