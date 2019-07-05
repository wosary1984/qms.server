package qms.config.datasource;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/*****
 * 
 * @author i068981
 * Embedded memory data source
 */
@Configuration
@Profile("h2")
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImp")
public class EmbeddedDatabaseConfig {
	/**
	 * Creates DataSource for an embedded Database (H2).
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Bean
	public DataSource dataSource() {
		logger.info("Build embedded H2 memory data source");
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

}
