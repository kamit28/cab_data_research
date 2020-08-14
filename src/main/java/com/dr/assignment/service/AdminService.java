package com.dr.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@Service
public class AdminService {

	private Jedis jedis;

	@Autowired
	public AdminService(Jedis jedis) {
		this.jedis = jedis;
	}

	public boolean clearCache() throws RuntimeException {
		jedis.flushDB();
		return true;
	}
}
