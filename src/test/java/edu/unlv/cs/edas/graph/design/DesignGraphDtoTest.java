package edu.unlv.cs.edas.graph.design;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.unlv.cs.edas.graph.domain.Key;
import edu.unlv.cs.graph.EdgeKey;

public class DesignGraphDtoTest {

	@Test
	public void testJsonSerializationWrite() throws Exception {
		List<DesignVertex> vertices = new ArrayList<DesignVertex>();
		
		DesignVertex vertexA = new DesignVertex(new Key("A"));
		vertexA.setPosition(new Position(1, 1));
		vertices.add(vertexA);
		
		DesignVertex vertexB = new DesignVertex(new Key("B"));
		vertexB.setPosition(new Position(1, 1));
		vertices.add(vertexB);
		
		List<DesignEdge> edges = new ArrayList<DesignEdge>();
		
		DesignEdge edge = new DesignEdge(new EdgeKey<Key>(new Key("A"), new Key("B")));
		edges.add(edge);
		
		DesignGraphDto dto = new DesignGraphDto();
		dto.setVertices(vertices);
		dto.setEdges(edges);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonValue = objectMapper.writeValueAsString(dto);
		assertNotNull(jsonValue);
	}
	
	@Test
	public void testJsonSerializationRead() throws Exception {
		InputStream in = null;
		try {
			in = getClass().getClassLoader().getResourceAsStream("design-graph-sample.txt");
		
			ObjectMapper objectMapper = new ObjectMapper();
			DesignGraphDto graphDto = objectMapper.readValue(in, DesignGraphDto.class);
			assertNotNull(graphDto);
			
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
	
}
