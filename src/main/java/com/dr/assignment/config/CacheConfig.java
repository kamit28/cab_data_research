package com.dr.assignment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.dr.assignment.model.TripBooking;

import lombok.Data;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "redis")
public class CacheConfig {

	private String host;

	private int port;

	private int poolMaxActive;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		final var poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(poolMaxActive);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(true);

		var standaloneConfiguration = new RedisStandaloneConfiguration(host, port);
		final var connectionFactory = new JedisConnectionFactory(standaloneConfiguration);

		return connectionFactory;
	}

	@Bean
	public RedisTemplate<String, TripBooking> redisTemplate() {
		final var redisTemplate = new RedisTemplate<String, TripBooking>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<TripBooking>(TripBooking.class));
		return redisTemplate;
	}

	@Bean
	public Jedis jedis() {
		return new Jedis(host, port);
	}
}
