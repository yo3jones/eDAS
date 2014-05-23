package edu.unlv.cs.edas.spring.root;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import edu.unlv.cs.edas.design.dto.EdgeKeyReadConverter;
import edu.unlv.cs.edas.design.dto.EdgeKeyWriteConverter;

@EnableMongoRepositories(basePackages={"edu.unlv.cs.edas"})
public class MongoConfig extends AbstractMongoConfiguration {
	
	@Bean
	public Mongo mongo() throws UnknownHostException {
		return new MongoClient("localhost");
	}
	
	@Override
	protected String getDatabaseName() {
		return "edas";
	}
	
	@Bean
	@Override
	public CustomConversions customConversions() {
		List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
		converters.add(new EdgeKeyWriteConverter());
		converters.add(new EdgeKeyReadConverter());
		return new CustomConversions(converters);
	}
	
	@Override
	public MappingMongoConverter mappingMongoConverter() throws Exception {
		MappingMongoConverter converter = super.mappingMongoConverter();
		((GenericConversionService) converter.getConversionService()).addConverter(
				new EdgeKeyReadConverter());
		return converter;
	}
	
	@Override
	public MongoMappingContext mongoMappingContext() throws ClassNotFoundException {
		MongoMappingContext context = super.mongoMappingContext();
		context.afterPropertiesSet();
		return context;
	}
	
}
