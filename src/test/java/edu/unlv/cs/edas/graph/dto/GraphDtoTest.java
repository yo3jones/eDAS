package edu.unlv.cs.edas.graph.dto;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class GraphDtoTest {

	private GraphDto<String, VertexDto, EdgeDto> graph;
	
	@Before
	public void setUp() {
		graph = new GraphDto<String, VertexDto, EdgeDto>();
	}
	
	@Test
	public void testGetVertices() {
		Map<String, VertexDto> verticesMap = new HashMap<String, VertexDto>();
		graph.setVertices(verticesMap);
		assertTrue(verticesMap == graph.getVertices());
	}
	
	@Test
	public void testGetEdges() {
		Map<String, EdgeDto> edgesMap = new HashMap<String, EdgeDto>();
		graph.setEdges(edgesMap);
		assertTrue(edgesMap == graph.getEdges());
	}
	
	@Test
	public void testAddVertex() {
		VertexDto vertex = mock(VertexDto.class);
		graph.addVertex("1", vertex);
		assertEquals(vertex, graph.getVertices().get("1"));
	}
	
	@Test
	public void tsetAddEdge() {
		EdgeDto edge = mock(EdgeDto.class);
		graph.addEdge("1", "2", edge);
		assertEquals(edge, graph.getEdges().get("1-2"));
	}
	
	@Test
	public void testEquals() {
		assertFalse(graph.equals(null));
		assertTrue(graph.equals(graph));
		assertFalse(graph.equals(this));
		
		GraphDto<String, VertexDto, EdgeDto> graph2 = new GraphDto<String, VertexDto, EdgeDto>();
		assertTrue(graph.equals(graph2));
		
		graph2.addVertex("1", mock(VertexDto.class));
		assertFalse(graph.equals(graph2));
	}
	
}
