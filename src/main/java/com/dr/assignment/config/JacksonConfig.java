package com.dr.assignment.config;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.format.FormatMapper;
import org.hibernate.type.format.jackson.JacksonJsonFormatMapper;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonConfig implements FormatMapper {

	private final FormatMapper delegate;

	public JacksonConfig() {
		ObjectMapper mapper = objectMapper();
		delegate = new JacksonJsonFormatMapper(mapper);
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return mapper;
	}

	@Override
	public <T> T fromString(CharSequence charSequence, JavaType<T> javaType, WrapperOptions wrapperOptions) {
		return delegate.fromString(charSequence, javaType, wrapperOptions);
	}

	@Override
	public <T> String toString(T value, JavaType<T> javaType, WrapperOptions wrapperOptions) {
		return delegate.toString(value, javaType, wrapperOptions);
	}
}
