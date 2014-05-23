package edu.unlv.cs.edas.design.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import com.mongodb.DBObject;

import edu.unlv.cs.graph.EdgeKey;

@WritingConverter
public class EdgeKeyWriteConverter implements Converter<EdgeKey<?>, DBObject> {

	@Override
	public DBObject convert(EdgeKey<?> source) {
		return null;
	}
	
}
