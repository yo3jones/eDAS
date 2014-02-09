package edu.unlv.cs.edas.spring.api;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"edu.unlv.cs.edas.graph.design.controller.api"})
public class ApiWebConfig extends WebMvcConfigurationSupport {
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		stringConverter.setWriteAcceptCharset(false);
		converters.add(stringConverter);
		converters.add(getConverter());
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter getConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.getObjectMapper().configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		converter.getObjectMapper().configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		return converter;
	}
	
}
