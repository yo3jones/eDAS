package edu.unlv.cs.edas.design.dto;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import edu.unlv.cs.graph.EdgeKey;

public class JsonEdgeKeyDeserializer extends KeyDeserializer {

	@Override
	public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, 
			JsonProcessingException {
		String[] parts = key.split("-");
		return new EdgeKey<Integer>(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
	}

}
