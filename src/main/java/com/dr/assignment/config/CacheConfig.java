package com.dr.assignment.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.Jedis;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(RedisProperties.class)
public class CacheConfig {

	private String host;

	private int port;

	//private int poolMaxActive;

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		final var redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(new JacksonConfig().objectMapper()));

		return redisTemplate;
	}

	@Bean
	public Jedis jedis() {
		return new Jedis(host, port);
	}
}
