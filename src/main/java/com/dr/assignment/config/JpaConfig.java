package com.dr.assignment.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableJpaRepositories("com.dr.assignment.repository")
@EntityScan(basePackageClasses = { Jsr310JpaConverters.class }, basePackages = { "com.dr.assignment.entity" })
@ConfigurationProperties(prefix = "jpa")
public class JpaConfig {
	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		final var dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("datasource.driverClassName"));
		dataSource.setUrl(env.getProperty("datasource.url"));
		dataSource.setUsername(env.getProperty("datasource.username"));
		dataSource.setPassword(env.getProperty("datasource.password"));

		return dataSource;
	}

}