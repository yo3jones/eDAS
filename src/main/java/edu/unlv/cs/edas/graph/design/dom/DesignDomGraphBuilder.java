package edu.unlv.cs.edas.graph.design.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.unlv.cs.edas.graph.design.DesignEdge;
import edu.unlv.cs.edas.graph.design.DesignVertex;
import edu.unlv.cs.edas.graph.design.Position;
import edu.unlv.cs.edas.graph.domain.Key;
import edu.unlv.cs.graph.EdgeKey;
import edu.unlv.cs.graph.Graph;
import edu.unlv.cs.graph.dom.domain.GraphDomBuilder;

/**
 * A {@link GraphDomBuilder} implementation for creating a SVG DOM for a design graph.
 * 
 * @author Chris Jones
 */
public class DesignDomGraphBuilder implements GraphDomBuilder<Key, DesignVertex, DesignEdge, 
		DesignDomGraphBuilderContext> {

	private static final Integer RADIUS = 20;
	
	@Override
	public Document createDocument() {
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			return document;
		} catch (ParserConfigurationException e) {
			throw new RuntimeException("Error creating SVG DOM Document.", e);
		}
	}

	@Override
	public Element createRootElement(Document document) {
		Element rootElement = document.createElementNS("http://www.w3.org/2000/svg", "svg");
		return rootElement;
	}
	
	@Override
	public DesignDomGraphBuilderContext createContext(Document document, Element rootElement, 
			Graph<Key, DesignVertex, DesignEdge> graph) {
		rootElement.setAttribute("version", "1.1");
		rootElement.setAttribute("width", "100%");
		rootElement.setAttribute("height", "100%");
		return new DesignDomGraphBuilderContext(document, rootElement, graph);
	}

	@Override
	public Element createVertex(DesignDomGraphBuilderContext context, Key key, DesignVertex vertex) {
		Document document = context.getDocument();
		Position position = vertex.getPosition();
		Integer id = key.getId();
		
		Element groupElement = document.createElement("g");
		
		Element vertexElement = document.createElement("circle");
		vertexElement.setAttribute("id", "-v-" + id);
		vertexElement.setAttribute("vertexId", id.toString());
		vertexElement.setAttribute("r", RADIUS.toString());
		vertexElement.setAttribute("cx", position.getX().toString());
		vertexElement.setAttribute("cy", position.getY().toString());
		groupElement.appendChild(vertexElement);
		
		Element textElement = document.createElement("text");
		textElement.setAttribute("id", "-l-" + id);
		textElement.setAttribute("vertexId", id.toString());
		textElement.setAttribute("x", position.getX().toString());
		textElement.setAttribute("y", position.getY().toString());
		textElement.setTextContent(vertex.getLabel());
		groupElement.appendChild(textElement);
		
		return groupElement;
	}

	@Override
	public Element createEdge(DesignDomGraphBuilderContext context, EdgeKey<Key> key, 
			DesignEdge edge) {
		Document document = context.getDocument();
		
		DesignVertex beginVertex = context.getGraph().getVertex(key.getFromKey());
		DesignVertex endVertex = context.getGraph().getVertex(key.getToKey());
		
		Element groupElement = document.createElement("g");
		
		String edgeId = key.getFromKey().getId() + "-" + key.getToKey().getId();
		String vertexId1 = key.getFromKey().getId().toString();
		String vertexId2 = key.getToKey().getId().toString();
		Position vertexPosition1 = beginVertex.getPosition();
		Position vertexPosition2 = endVertex.getPosition();
		
		Element edgeElement = document.createElement("line");
		edgeElement.setAttribute("id", "-e-" + edgeId);
		edgeElement.setAttribute("edgeId", edgeId);
		edgeElement.setAttribute("vertexId1", vertexId1);
		edgeElement.setAttribute("vertexId2", vertexId2);
		edgeElement.setAttribute("x1", vertexPosition1.getX().toString());
		edgeElement.setAttribute("y1", vertexPosition1.getY().toString());
		edgeElement.setAttribute("x2", vertexPosition2.getX().toString());
		edgeElement.setAttribute("y2", vertexPosition2.getY().toString());
		groupElement.appendChild(edgeElement);
		
		Position mid = getMidPosition(vertexPosition1, vertexPosition2);
		Position weightOffset = getWeightOffset(vertexPosition1, vertexPosition2);
		
		Element weightElement = document.createElement("text");
		weightElement.setAttribute("id", "-w-" + edgeId);
		weightElement.setAttribute("edgeId", edgeId);
		weightElement.setAttribute("vertexId1", vertexId1);
		weightElement.setAttribute("vertexId2", vertexId2);
		weightElement.setAttribute("x", mid.getX().toString());
		weightElement.setAttribute("y", mid.getY().toString());
		weightElement.setAttribute("dx", weightOffset.getX().toString());
		weightElement.setAttribute("dy", weightOffset.getY().toString());
		weightElement.setTextContent(edge.getWeight() == null ? "" : edge.getWeight().toString());
		groupElement.appendChild(weightElement);
		
		return groupElement;
	}

	private Position getMidPosition(Position p1, Position p2) {
		Integer midX = (p1.getX() + p2.getX()) / 2;
		Integer midY = (p1.getY() + p2.getY()) / 2;
		return new Position(midX, midY);
	}
	
	private Position getWeightOffset(Position p1, Position p2) {
		Integer lineDx = p1.getX() - p2.getX();
		Integer lineDy = p1.getY() - p2.getY();
		
		Integer dx = null;
		Integer dy = null;
		
		Integer d = RADIUS;
		
		if (lineDx == 0) {
			dx = d;
			dy = 0;
		} else if (lineDy == 0) {
			dx = 0;
			dy = d;
		} else {
			Double lineM = (double) lineDy / lineDx;
			Double m = -1 * (1 / lineM);
			Double k = Math.sqrt(1 + m * m);
			dx = (int) (d / k);
			dy = (int) ((m * d) / k);
		}
		
		return new Position(dx, dy);
	}
	
}
