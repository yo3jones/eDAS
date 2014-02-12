package edu.unlv.cs.edas.graph.design.adapter.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.unlv.cs.edas.graph.design.DesignGraph;
import edu.unlv.cs.edas.graph.design.dto.DesignEdgeDto;
import edu.unlv.cs.edas.graph.design.dto.DesignGraphDto;
import edu.unlv.cs.edas.graph.design.dto.DesignVertexDto;

public class DesignGraphAdapterImplTest {

	private DesignGraphAdapterImpl adapter;
	
	@Before
	public void setUp() {
		adapter = new DesignGraphAdapterImpl();
	}
	
	@Test
	public void testCreateGraph() throws Exception {
		DesignGraphDto graphDto = new DesignGraphDto();
		
		DesignVertexDto vertexDto = new DesignVertexDto();
		vertexDto.setLabel("A");
		vertexDto.setX(1);
		vertexDto.setY(2);
		graphDto.addVertex(1, vertexDto);
		
		vertexDto = new DesignVertexDto();
		vertexDto.setLabel("B");
		vertexDto.setX(3);
		vertexDto.setY(4);
		graphDto.addVertex(2, vertexDto);
		
		vertexDto = new DesignVertexDto();
		vertexDto.setLabel("C");
		vertexDto.setX(5);
		vertexDto.setY(6);
		graphDto.addVertex(3, vertexDto);
		
		graphDto.addEdge(1, 2, new DesignEdgeDto());
		graphDto.addEdge(1, 3, new DesignEdgeDto());
		
		DesignGraph graph = adapter.createGraph(graphDto);
		DesignGraphDto actual = adapter.createDto(graph);
		
		assertEquals(graphDto, actual);
		
	}
	
}
