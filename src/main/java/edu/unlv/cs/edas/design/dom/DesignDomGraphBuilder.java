package edu.unlv.cs.edas.design.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.unlv.cs.edas.design.domain.DesignEdge;
import edu.unlv.cs.edas.design.domain.DesignVertex;
import edu.unlv.cs.edas.design.domain.Position;
import edu.unlv.cs.graph.EdgeKey;
import edu.unlv.cs.graph.Graph;
import edu.unlv.cs.graph.dom.domain.GraphDomBuilder;

/**
 * A {@link GraphDomBuilder} implementation for creating a SVG DOM for a design graph.
 * 
 * @author Chris Jones
 */
public class DesignDomGraphBuilder implements GraphDomBuilder<Integer, DesignVertex, DesignEdge, 
		DesignDomGraphBuilderContext> {

	private static final Integer RADIUS = 25;
	private static final Double WEIGHT_DISTANCE = 2.0;
	
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
			Graph<Integer, DesignVertex, DesignEdge> graph) {
		rootElement.setAttribute("version", "1.1");
		rootElement.setAttribute("width", "100%");
		rootElement.setAttribute("height", "100%");
		return new DesignDomGraphBuilderContext(document, rootElement, graph);
	}

	@Override
	public Element createVertex(DesignDomGraphBuilderContext context, Integer key, DesignVertex vertex) {
		Document document = context.getDocument();
		Position position = vertex.getPosition();
		Integer id = key;
		
		Element groupElement = document.createElement("g");
		groupElement.setAttribute("id", "-v-" + id);
		groupElement.setAttribute("vertexId", id.toString());
		groupElement.setAttribute("class", "vertex-container");
		
		Element vertexElement = document.createElement("circle");
		vertexElement.setAttribute("class", "vertex-circle");
		vertexElement.setAttribute("r", RADIUS.toString());
		vertexElement.setAttribute("cx", position.getX().toString() + "%");
		vertexElement.setAttribute("cy", position.getY().toString() + "%");
		groupElement.appendChild(vertexElement);
		
		Element textElement = document.createElement("text");
		textElement.setAttribute("class", "vertex-label");
		textElement.setAttribute("x", position.getX().toString() + "%");
		textElement.setAttribute("y", position.getY().toString() + "%");
		textElement.setTextContent(vertex.getLabel());
		groupElement.appendChild(textElement);
		
		return groupElement;
	}

	@Override
	public Element createEdge(DesignDomGraphBuilderContext context, EdgeKey<Integer> key, 
			DesignEdge edge) {
		Document document = context.getDocument();
		
		DesignVertex beginVertex = context.getGraph().getVertex(key.getFromKey());
		DesignVertex endVertex = context.getGraph().getVertex(key.getToKey());
		
		String edgeId = key.getFromKey() + "-" + key.getToKey();
		String vertexId1 = key.getFromKey().toString();
		String vertexId2 = key.getToKey().toString();
		
		Element groupElement = document.createElement("g");
		groupElement.setAttribute("class", "edge-container edge-container-" + vertexId1 
				+ " edge-container-" + vertexId2);
		groupElement.setAttribute("id", "-e-" + edgeId);
		groupElement.setAttribute("edgeId", edgeId);
		groupElement.setAttribute("vertexId1", vertexId1);
		groupElement.setAttribute("vertexId2", vertexId2);
		groupElement.setAttribute("weightDistance", WEIGHT_DISTANCE.toString());
		
		Position vertexPosition1 = beginVertex.getPosition();
		Position vertexPosition2 = endVertex.getPosition();
		
		Element edgeSelectElement = document.createElement("line");
		edgeSelectElement.setAttribute("class", "edge-line-select");
		edgeSelectElement.setAttribute("x1", vertexPosition1.getX().toString() + "%");
		edgeSelectElement.setAttribute("y1", vertexPosition1.getY().toString() + "%");
		edgeSelectElement.setAttribute("x2", vertexPosition2.getX().toString() + "%");
		edgeSelectElement.setAttribute("y2", vertexPosition2.getY().toString() + "%");
		groupElement.appendChild(edgeSelectElement);
		
		Element edgeElement = document.createElement("line");
		edgeElement.setAttribute("class", "edge-line");
		edgeElement.setAttribute("x1", vertexPosition1.getX().toString() + "%");
		edgeElement.setAttribute("y1", vertexPosition1.getY().toString() + "%");
		edgeElement.setAttribute("x2", vertexPosition2.getX().toString() + "%");
		edgeElement.setAttribute("y2", vertexPosition2.getY().toString() + "%");
		groupElement.appendChild(edgeElement);
		
		Position mid = getMidPosition(vertexPosition1, vertexPosition2);
		Position weightOffset = getWeightOffset(vertexPosition1, vertexPosition2);
		
		Element weightElement = document.createElement("text");
		weightElement.setAttribute("class", "edge-weight");
		weightElement.setAttribute("x", mid.getX().toString() + "%");
		weightElement.setAttribute("y", mid.getY().toString() + "%");
		weightElement.setAttribute("dx", weightOffset.getX().toString() + "%");
		weightElement.setAttribute("dy", weightOffset.getY().toString() + "%");
		weightElement.setTextContent(edge.getWeight() == null ? "" : edge.getWeight().toString());
		groupElement.appendChild(weightElement);
		
		return groupElement;
	}

	private Position getMidPosition(Position p1, Position p2) {
		Double midX = (p1.getX() + p2.getX()) / 2.0;
		Double midY = (p1.getY() + p2.getY()) / 2.0;
		return new Position(midX, midY);
	}
	
	private Position getWeightOffset(Position p1, Position p2) {
		Double lineDx = p1.getX() - p2.getX();
		Double lineDy = p1.getY() - p2.getY();
		
		Double dx = null;
		Double dy = null;
		
		Double d = WEIGHT_DISTANCE;
		
		if (lineDx == 0) {
			dx = d;
			dy = 0.0;
		} else if (lineDy == 0) {
			dx = 0.0;
			dy = d;
		} else {
			Double lineM = lineDy / lineDx;
			Double m = -1 * (1 / lineM);
			Double k = Math.sqrt(1 + m * m);
			dx = (d / k);
			dy = ((m * d) / k);
		}
		
		return new Position(dx, dy);
	}
	
}
