package edu.unlv.cs.edas.design.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import edu.unlv.cs.graph.EdgeKey;

@ReadingConverter
public class EdgeKeyReadConverter implements Converter<String, EdgeKey<?>> {

	@Override
	public EdgeKey<?> convert(String source) {
		String[] parts = source.split("-");
		return new EdgeKey<Integer>(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
	}
	
}
