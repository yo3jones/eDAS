package edu.unlv.cs.edas.design.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import edu.unlv.cs.edas.design.dom.DesignGraphDomAdapter;
import edu.unlv.cs.edas.design.domain.DesignEdge;
import edu.unlv.cs.edas.design.domain.DesignGraph;
import edu.unlv.cs.edas.design.domain.DesignGraphDetails;
import edu.unlv.cs.edas.design.domain.DesignVertex;
import edu.unlv.cs.edas.design.domain.Position;
import edu.unlv.cs.edas.design.manager.DesignGraphDetailsManager;
import edu.unlv.cs.edas.user.domain.UserManager;
import edu.unlv.cs.graph.EdgeKey;
import edu.unlv.cs.graph.Graph;

/**
 * A rest controller for interacting with design graphs.
 * 
 * @author Chris Jones
 *
 */
@RestController
@RequestMapping("/{version}/design/graphDetails")
public class DesignGraphDetailsApiController {
	
	/**
	 * A manager for storing graphs.
	 */
	@Inject DesignGraphDetailsManager manager;
	
	@Inject UserManager userManager;
	
	/**
	 * An adapter for creating an SVG DOM of a graph.
	 */
	@Inject DesignGraphDomAdapter domAdapter;
	
	/**
	 * Called when a client PUTs a graph.
	 * 
	 * @param id
	 *            The ID of the graph.
	 * @param graphDto
	 *            The graph DTO of the graph being updated.
	 */
	@RequestMapping(value="/{id}", method=PUT)
	public void putDesignGraph(@PathVariable String id, 
			@RequestBody DesignGraphDetails graphDetails) {
		DesignGraphDetails existingGraphDetails = manager.get(id);
		graphDetails.setOwner(existingGraphDetails.getOwner());
		manager.save(graphDetails);
	}
	
	@RequestMapping(value="/{id}", method=DELETE)
	public void deleteDesignGraph(@PathVariable String id) {
		manager.delete(id);
	}
	
	/**
	 * Called when a client GETs a graph expecting a JSON response.
	 * 
	 * @param id
	 *            The ID of the graph to return.
	 * @return A graph DTO of the graph requested.
	 */
	@RequestMapping(value="/{id}", method=GET, produces="application/json")
	public DesignGraphDetails getDesignGraphJson(@PathVariable String id) {
		DesignGraphDetails graphDetails = manager.get(id);
		return graphDetails;
	}

	/**
	 * Called when a client GETs a graph expecting an SVG response.
	 * 
	 * @param id
	 *            The ID of the graph to return.
	 * @return The SVG XML document string of the graph.
	 * @throws Exception
	 */
	@RequestMapping(value="/{id}", method=GET, produces="image/svg+xml")
	public String getGraphSvg(@PathVariable String id) throws Exception {
		DesignGraphDetails graphDetails = manager.get(id);
		DesignGraph graph = graphDetails.getGraph();
		
		Document document = domAdapter.getDomFromGraph(graph);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StreamResult result = new StreamResult(out);
		transformer.transform(source, result);
		
		out.flush();
		
		return out.toString();
	}
	
	/**
	 * Called when a client POSTs a vertex.
	 * 
	 * @param id
	 *            The id of the graph the vertex will be added to.
	 * @return The vertex DTO of the vertex created.
	 */
	@RequestMapping(value="/{id}/vertices", method=POST)
	public DesignVertex postVertex(@PathVariable String id) {
		DesignGraphDetails graphDetails = manager.get(id);
		DesignGraph graph = graphDetails.getGraph();
		Integer vertexKey = getNextVertexId(graph);
		
		DesignVertex vertex = new DesignVertex();
		vertex.setLabel("");
		Position position = new Position(5.0, 5.0);
		vertex.setPosition(position);
		
		graph.putVertex(vertexKey, vertex);
		
		manager.save(graphDetails);
		
		return vertex;
	}
	
	/**
	 * Called when a client PUTs a vertex.
	 * 
	 * @param id
	 *            The ID of the graph being updated.
	 * @param vertexId
	 *            The ID of the vertex being updated.
	 * @param vertexDto
	 *            The vertex DTO that contains the updates from the client.
	 */
	@RequestMapping(value="/{id}/vertices/{vertexId}", method=PUT)
	public void putVertex(@PathVariable String id, @PathVariable Integer vertexId, 
			@RequestBody DesignVertex vertex) {
		DesignGraphDetails graphDetails = manager.get(id);
		DesignGraph graph = graphDetails.getGraph();
		graph.putVertex(vertexId, vertex);
		
		manager.save(graphDetails);
	}
	
	/**
	 * Called when a user DELETEs a vertex.
	 * 
	 * @param id
	 *            The ID of the graph being updated.
	 * @param vertexId
	 *            The ID of the vertex being deleted.
	 */
	@RequestMapping(value="/{id}/vertices/{vertexId}", method=DELETE)
	public void deleteVertex(@PathVariable String id, @PathVariable Integer vertexId) {
		DesignGraphDetails graphDetails = manager.get(id);
		DesignGraph graph = graphDetails.getGraph();
		graph.removeVertex(vertexId);
		
		manager.save(graphDetails);
	}
	
	/**
	 * Called when a user POSTs an edge.
	 * 
	 * @param id
	 *            The ID of the graph being updated.
	 * @param fromVertexId
	 *            The ID of the start vertex of the edge being added.
	 * @param toVertexId
	 *            The ID of the end vertex of the edge being added.
	 * @return A DTO of the edge that was created.
	 */
	@RequestMapping(value="/{id}/edges/{fromVertexId}-{toVertexId}", method=POST)
	public DesignEdge postEdge(@PathVariable String id, @PathVariable Integer fromVertexId, 
			@PathVariable Integer toVertexId) {
		DesignGraphDetails graphDetails = manager.get(id);
		DesignGraph designGraph = graphDetails.getGraph();
		
		EdgeKey<Integer> edgeId = new EdgeKey<Integer>(fromVertexId, toVertexId);
		DesignEdge edge = new DesignEdge();
		
		designGraph.putEdge(edgeId, edge);
		
		manager.save(graphDetails);
		
		return edge;
	}
	
	/**
	 * Called when a users PUTSs an edge.
	 * 
	 * @param id
	 *            The ID of the graph being updated.
	 * @param fromVertexId
	 *            The ID of the start vertex of the edge being updated.
	 * @param toVertexId
	 *            The ID of the end vertex of the edge being updated.
	 * @param edgeDto
	 *            The edge DTO with values to update on the edge.
	 */
	@RequestMapping(value="/{id}/edges/{fromVertexId}-{toVertexId}", method=PUT)
	public void putEdge(@PathVariable String id, @PathVariable Integer fromVertexId, 
			@PathVariable Integer toVertexId, @RequestBody DesignEdge edge) {
		DesignGraphDetails graphDetails = manager.get(id);
		DesignGraph graph = graphDetails.getGraph();
		
		EdgeKey<Integer> edgeId = new EdgeKey<Integer>(fromVertexId, toVertexId);
		graph.putEdge(edgeId, edge);
		
		manager.save(graphDetails);
	}
	
	/**
	 * Called when a client DELETEs an edge.
	 * 
	 * @param id
	 *            The ID of the graph being updated.
	 * @param fromVertexId
	 *            The ID of the start vertex of the edge being deleted.
	 * @param toVertexId
	 *            The ID of the end vertex of the edge being deleted.
	 */
	@RequestMapping(value="/{id}/edges/{fromVertexId}-{toVertexId}", method=DELETE)
	public void deleteEdge(@PathVariable String id, @PathVariable Integer fromVertexId, 
			@PathVariable Integer toVertexId) {
		DesignGraphDetails graphDetails = manager.get(id);
		DesignGraph graph = graphDetails.getGraph();
		EdgeKey<Integer> key = new EdgeKey<Integer>(fromVertexId, toVertexId);
		graph.removeEdge(key);
		
		manager.save(graphDetails);
	}
	
	private Integer getNextVertexId(Graph<Integer, ?, ?> graph) {
		Integer maxId = 0;
		for (Integer vertexId : graph.getVertexSet()) {
			maxId = Math.max(maxId, vertexId);
		}
		return maxId + 1;
	}
	
}
