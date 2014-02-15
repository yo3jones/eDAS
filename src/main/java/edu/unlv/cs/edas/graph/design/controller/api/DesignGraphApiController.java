package edu.unlv.cs.edas.graph.design.controller.api;

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

import edu.unlv.cs.edas.graph.design.DesignEdge;
import edu.unlv.cs.edas.graph.design.DesignGraph;
import edu.unlv.cs.edas.graph.design.DesignGraphId;
import edu.unlv.cs.edas.graph.design.DesignVertex;
import edu.unlv.cs.edas.graph.design.Position;
import edu.unlv.cs.edas.graph.design.adapter.DesignGraphAdapter;
import edu.unlv.cs.edas.graph.design.controller.api.response.CreateResponse;
import edu.unlv.cs.edas.graph.design.dom.DesignGraphDomAdapter;
import edu.unlv.cs.edas.graph.design.dto.DesignEdgeDto;
import edu.unlv.cs.edas.graph.design.dto.DesignGraphDto;
import edu.unlv.cs.edas.graph.design.dto.DesignVertexDto;
import edu.unlv.cs.edas.graph.design.manager.DesignGraphManager;
import edu.unlv.cs.edas.graph.domain.Key;
import edu.unlv.cs.graph.EdgeKey;

/**
 * A rest controller for interacting with design graphs.
 * 
 * @author Chris Jones
 *
 */
@RestController
@RequestMapping("/{version}/design/graphs")
public class DesignGraphApiController {
	
	/**
	 * An adapter for converting graph objects to DTOs and back.
	 */
	@Inject DesignGraphAdapter adapter;
	
	/**
	 * A manager for storing graphs.
	 */
	@Inject DesignGraphManager manager;
	
	/**
	 * An adapter for creating an SVG DOM of a graph.
	 */
	@Inject DesignGraphDomAdapter domAdapter;
	
	/**
	 * Called when a client POSTs a graph.
	 * 
	 * @return A Response containing the id of the graph created.
	 */
	@RequestMapping(method=POST)
	public CreateResponse postGraph() {
		DesignGraph graph = new DesignGraph();
		DesignGraphId graphId = manager.addGraph(graph);
		
		CreateResponse response = new CreateResponse();
		response.setId(graphId.getId());
		return response;
	}
	
	/**
	 * Called when a client PUTs a graph.
	 * 
	 * @param id
	 *            The ID of the graph.
	 * @param graphDto
	 *            The graph DTO of the graph being updated.
	 */
	@RequestMapping(value="/{id}", method=PUT)
	public void putGraph(@PathVariable Long id, @RequestBody DesignGraphDto graphDto) {
		DesignGraph graph = getGraph(id);
		adapter.updateGraphFromDto(graph, graphDto);
	}
	
	/**
	 * Called when a client GETs a graph expecting a JSON response.
	 * 
	 * @param id
	 *            The ID of the graph to return.
	 * @return A graph DTO of the graph requested.
	 */
	@RequestMapping(value="/{id}", method=GET, produces="application/json")
	public DesignGraphDto getGraphJson(@PathVariable Long id) {
		DesignGraphId graphId = new DesignGraphId(id);
		DesignGraph graph = manager.getGraph(graphId);
		DesignGraphDto dto = adapter.createDto(graph);
		return dto;
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
	public String getGraphSvg(@PathVariable Long id) throws Exception {
		DesignGraphId graphId = new DesignGraphId(id);
		DesignGraph graph = manager.getGraph(graphId);
		
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
	public DesignVertexDto postVertex(@PathVariable Long id) {
		DesignGraph graph = getGraph(id);
		
		Key key = new Key(graph.getNextVertexId());
		DesignVertex vertex = new DesignVertex();
		vertex.setLabel("");
		Position position = new Position(50, 50);
		vertex.setPosition(position);
		
		graph.putVertex(key, vertex);
		
		return adapter.createDtoVertex(vertex);
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
	public void putVertex(@PathVariable Long id, @PathVariable Integer vertexId, 
			@RequestBody DesignVertexDto vertexDto) {
		DesignVertex vertex = adapter.createVertex(vertexDto);
		
		DesignGraph graph = getGraph(id);
		graph.putVertex(new Key(vertexId), vertex);
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
	public void deleteVertex(@PathVariable Long id, @PathVariable Integer vertexId) {
		DesignGraph graph = getGraph(id);
		graph.removeVertex(new Key(vertexId));
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
	public DesignEdgeDto postEdge(@PathVariable Long id, @PathVariable Integer fromVertexId, 
			@PathVariable Integer toVertexId) {
		DesignGraph designGraph = getGraph(id);
		
		EdgeKey<Key> edgeId = new EdgeKey<Key>(new Key(fromVertexId), new Key(toVertexId));
		DesignEdge edge = new DesignEdge();
		
		designGraph.putEdge(edgeId, edge);
		
		return adapter.createDtoEdge(edge);
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
	public void putEdge(@PathVariable Long id, @PathVariable Integer fromVertexId, 
			@PathVariable Integer toVertexId, @RequestBody DesignEdgeDto edgeDto) {
		DesignGraph designGraph = getGraph(id);
		
		EdgeKey<Key> edgeId = new EdgeKey<Key>(new Key(fromVertexId), new Key(toVertexId));
		DesignEdge edge = adapter.createEdge(edgeDto);
		designGraph.putEdge(edgeId, edge);
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
	public void deleteEdge(@PathVariable Long id, @PathVariable Integer fromVertexId, 
			@PathVariable Integer toVertexId) {
		DesignGraph designGraph = getGraph(id);
		EdgeKey<Key> key = new EdgeKey<Key>(new Key(fromVertexId), new Key(toVertexId));
		designGraph.removeEdge(key);
	}
	
	/**
	 * Returns a graph with the given ID.
	 * 
	 * @param id
	 *            The ID of the graph.
	 * @return A graph with the given ID.
	 */
	private DesignGraph getGraph(Long id) {
		return manager.getGraph(new DesignGraphId(id));
	}
	
}
