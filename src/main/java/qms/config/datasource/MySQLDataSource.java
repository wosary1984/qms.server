package qms.config.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.zaxxer.hikari.HikariDataSource;
/**
 * <p>Company: Feng </p> 
 * @Description: 显示创建Hikari Data Source，
 * @Create Date: 2018/03/29
 * @Version: V1.00 
 * @Author: Feng
 */
@Configuration
@Profile("mysql")
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImp")
public class MySQLDataSource {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean
	@ConfigurationProperties("spring.datasource")
	public HikariDataSource dataSource() {
		logger.info("Build Hikari data source");
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

}
