package edu.unlv.cs.edas.graph.design.controller.api;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.unlv.cs.edas.graph.design.DesignGraph;
import edu.unlv.cs.edas.graph.design.DesignGraphDto;
import edu.unlv.cs.edas.graph.design.DesignGraphId;
import edu.unlv.cs.edas.graph.design.adapter.DesignGraphAdapter;
import edu.unlv.cs.edas.graph.design.controller.api.response.CreateResponse;
import edu.unlv.cs.edas.graph.design.manager.DesignGraphManager;

@RestController
@RequestMapping("/{version}/design/graph")
public class DesignGraphApiController {

	@Inject DesignGraphAdapter adapter;
	@Inject DesignGraphManager manager;
	
	@RequestMapping(method=RequestMethod.POST)
	public CreateResponse handleCreateGraph(@RequestBody DesignGraphDto graphDto) {
		DesignGraph graph = adapter.createGraph(graphDto);
		DesignGraphId graphId = manager.addGraph(graph);
		
		CreateResponse response = new CreateResponse();
		response.setId(graphId.getId());
		return response;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public DesignGraphDto handleGetGraph(@PathVariable Long id) {
		DesignGraphId graphId = new DesignGraphId(id);
		DesignGraph graph = manager.getGraph(graphId);
		DesignGraphDto dto = adapter.createDto(graph);
		return dto;
	}
	
}
