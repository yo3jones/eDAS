package edu.unlv.cs.edas.spring.api;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="edu.unlv.cs.edas", includeFilters=@Filter(RestController.class))
public class ApiWebConfig extends WebMvcConfigurerAdapter {
	
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		stringConverter.setWriteAcceptCharset(false);
		converters.add(stringConverter);
		converters.add(getConverter());
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter getConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.getObjectMapper().configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
		converter.getObjectMapper().configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		converter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return converter;
	}
	
}
