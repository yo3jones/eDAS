package edu.unlv.cs.graph.dom.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.unlv.cs.graph.EdgeKey;
import edu.unlv.cs.graph.Graph;
import edu.unlv.cs.graph.HashGraph;
import edu.unlv.cs.graph.HashGraphTest;
import edu.unlv.cs.graph.dom.domain.GraphDomBuilder;

public class GraphDomAdapterImplTest {
	
	private GraphDomAdapterImpl<String, String, String> adapter;
	
	private GraphDomBuilder<String, String, String, String> builder;
	
	private String context;
	private HashGraph<String, String, String> graph;
	private Document document;
	private Element rootElement;
	
	private Element[] vertexNodes;
	private Element[] edgeNodes;
	
	@SuppressWarnings("unchecked")
	private GraphDomBuilder<String, String, String, String> mockBuilder() {
		return mock(GraphDomBuilder.class);
	}
	
	@SuppressWarnings("unchecked")
	private Graph<String, String, String> anyGraph() {
		return any(Graph.class);
	}
	
	@SuppressWarnings("unchecked")
	private EdgeKey<String> anyEdgeKey() {
		return any(EdgeKey.class);
	}
	
	@Before
	public void setUp() throws Exception {
		document = mock(Document.class);
		rootElement = mock(Element.class);
		
		graph = HashGraphTest.toGraph(new String[][][] {
				new String[][] {
						new String[] {"one", "one"},
						new String[] {"two", "two"},
						new String[] {"three", "three"}},
				new String[][] {
						new String[] {"one", "two", "one-two"},
						new String[] {"one", "three", "one-three"}}});
		graph.putVertex("one", "one");
		
		builder = mockBuilder();
		context = "Some Context";
		
		when(builder.createContext(any(Document.class), any(Element.class), anyGraph()))
				.thenReturn(context);
		when(builder.createDocument()).thenReturn(document);
		when(builder.createRootElement(document)).thenReturn(rootElement);
		
		vertexNodes = new Element[] {mock(Element.class), mock(Element.class), mock(Element.class)};
		when(builder.createVertex(context, "one", "one")).thenReturn(vertexNodes[0]);
		when(builder.createVertex(context, "two", "two")).thenReturn(vertexNodes[1]);
		when(builder.createVertex(context, "three", "three")).thenReturn(vertexNodes[2]);
		
		edgeNodes = new Element[] {mock(Element.class), mock(Element.class)};
		when(builder.createEdge(context, new EdgeKey<String>("one", "two"), "one-two"))
				.thenReturn(edgeNodes[0]);
		when(builder.createEdge(context, new EdgeKey<String>("one", "three"), "one-three"))
		.thenReturn(edgeNodes[1]);
		
		adapter = new GraphDomAdapterImpl<String, String, String>();
		adapter.setBuilder(builder);
	}
	
	@Test
	public void testGetDomFromGraph() throws Exception {
		Document actual = adapter.getDomFromGraph(graph);
		
		assertEquals(document, actual);
		verify(builder).createDocument();
		verify(builder).createContext(document, rootElement, graph);
		
		verify(builder, times(3)).createVertex(anyString(), anyString(), anyString());
		verify(builder).createVertex(context, "one", "one");
		verify(builder).createVertex(context, "two", "two");
		verify(builder).createVertex(context, "three", "three");
		
		verify(rootElement).appendChild(vertexNodes[0]);
		verify(rootElement).appendChild(vertexNodes[1]);
		verify(rootElement).appendChild(vertexNodes[2]);
		
		verify(builder, times(2)).createEdge(anyString(), anyEdgeKey(), anyString());
		verify(builder).createEdge(context, new EdgeKey<String>("one", "two"), "one-two");
		verify(builder).createEdge(context, new EdgeKey<String>("one", "three"), "one-three");
		
		verify(rootElement).appendChild(edgeNodes[0]);
		verify(rootElement).appendChild(edgeNodes[1]);
	}
	
}
