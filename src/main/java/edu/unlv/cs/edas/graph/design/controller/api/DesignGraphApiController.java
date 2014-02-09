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
import org.springframework.web.servlet.support.WebContentGenerator;
import org.w3c.dom.Document;

import edu.unlv.cs.edas.graph.design.DesignEdge;
import edu.unlv.cs.edas.graph.design.DesignGraph;
import edu.unlv.cs.edas.graph.design.DesignGraphDto;
import edu.unlv.cs.edas.graph.design.DesignGraphId;
import edu.unlv.cs.edas.graph.design.DesignVertex;
import edu.unlv.cs.edas.graph.design.Position;
import edu.unlv.cs.edas.graph.design.adapter.DesignGraphAdapter;
import edu.unlv.cs.edas.graph.design.controller.api.response.CreateResponse;
import edu.unlv.cs.edas.graph.design.dom.DesignGraphDomAdapter;
import edu.unlv.cs.edas.graph.design.manager.DesignGraphManager;
import edu.unlv.cs.edas.graph.domain.Key;
import edu.unlv.cs.graph.EdgeKey;

@RestController
@RequestMapping("/{version}/design/graphs")
public class DesignGraphApiController {

	@Inject DesignGraphAdapter adapter;
	@Inject DesignGraphManager manager;
	@Inject DesignGraphDomAdapter domAdapter;
	@Inject WebContentGenerator generator;
	
	@RequestMapping(method=POST)
	public CreateResponse postGraph(@RequestBody DesignGraphDto graphDto) {
		DesignGraph graph = adapter.createGraph(graphDto);
		DesignGraphId graphId = manager.addGraph(graph);
		
		CreateResponse response = new CreateResponse();
		response.setId(graphId.getId());
		return response;
	}
	
	@RequestMapping(method=POST, params="empty=true")
	public CreateResponse postEmptyGraph() {
		DesignGraph graph = new DesignGraph();
		DesignGraphId graphId = manager.addGraph(graph);
		
		CreateResponse response = new CreateResponse();
		response.setId(graphId.getId());
		return response;
	}
	
	@RequestMapping(value="/{id}", method=PUT)
	public void putGraph(@PathVariable Long id, @RequestBody DesignGraphDto graphDto) {
		DesignGraph graph = getGraph(id);
		adapter.updateGraphFromDto(graph, graphDto);
	}
	
	@RequestMapping(value="/{id}", method=GET, produces="application/json")
	public DesignGraphDto getGraphJson(@PathVariable Long id) {
		DesignGraphId graphId = new DesignGraphId(id);
		DesignGraph graph = manager.getGraph(graphId);
		DesignGraphDto dto = adapter.createDto(graph);
		return dto;
	}

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
	
	@RequestMapping(value="/{id}/vertices", method=POST)
	public DesignVertex postVertex(@PathVariable Long id) {
		DesignGraph graph = getGraph(id);
		
		Key key = new Key(graph.getNextVertexId());
		DesignVertex vertex = new DesignVertex(key);
		vertex.setLabel("");
		Position position = new Position(50, 50);
		vertex.setPosition(position);
		
		graph.putVertex(key, vertex);
		
		return vertex;
	}
	
	@RequestMapping(value="/{id}/vertices/{vertexName}", method=PUT)
	public void putVertex(@PathVariable Long id, @PathVariable String vertexName, 
			@RequestBody DesignVertex vertex) {
		DesignGraph graph = getGraph(id);
		graph.putVertex(vertex.getId(), vertex);
	}
	
	@RequestMapping(value="/{id}/vertices/{vertexId}", method=DELETE)
	public void deleteVertex(@PathVariable Long id, @PathVariable Integer vertexId) {
		DesignGraph graph = getGraph(id);
		graph.removeVertex(new Key(vertexId));
	}
	
	@RequestMapping(value="/{id}/edges/{fromVertexId}-{toVertexId}", method=POST)
	public DesignEdge postEdge(@PathVariable Long id, @PathVariable Integer fromVertexId, 
			@PathVariable Integer toVertexId) {
		DesignGraph designGraph = getGraph(id);
		
		EdgeKey<Key> edgeId = new EdgeKey<Key>(new Key(fromVertexId), new Key(toVertexId));
		DesignEdge edge = new DesignEdge(edgeId);
		
		designGraph.putEdge(edgeId, edge);
		
		return edge;
	}
	
	@RequestMapping(value="/{id}/edges/{fromVertexId}-{toVertexId}", method=DELETE)
	public void deleteEdge(@PathVariable Long id, @PathVariable Integer fromVertexId, 
			@PathVariable Integer toVertexId) {
		DesignGraph designGraph = getGraph(id);
		EdgeKey<Key> key = new EdgeKey<Key>(new Key(fromVertexId), new Key(toVertexId));
		designGraph.removeEdge(key);
	}
	
	private DesignGraph getGraph(Long id) {
		return manager.getGraph(new DesignGraphId(id));
	}
	
}
