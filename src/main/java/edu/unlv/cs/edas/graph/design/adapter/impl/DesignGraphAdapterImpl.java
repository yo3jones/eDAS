package edu.unlv.cs.edas.graph.design.adapter.impl;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.unlv.cs.edas.graph.adapter.GraphAdapter;
import edu.unlv.cs.edas.graph.adapter.impl.AbstractGraphAdapter;
import edu.unlv.cs.edas.graph.design.DesignEdge;
import edu.unlv.cs.edas.graph.design.DesignGraph;
import edu.unlv.cs.edas.graph.design.DesignVertex;
import edu.unlv.cs.edas.graph.design.Position;
import edu.unlv.cs.edas.graph.design.adapter.DesignGraphAdapter;
import edu.unlv.cs.edas.graph.design.dto.DesignEdgeDto;
import edu.unlv.cs.edas.graph.design.dto.DesignGraphDto;
import edu.unlv.cs.edas.graph.design.dto.DesignVertexDto;

/**
 * An implementation of {@link GraphAdapter} for design graphs.
 * 
 * @author Chris Jones
 */
@Component @Scope(SCOPE_PROTOTYPE)
public class DesignGraphAdapterImpl extends AbstractGraphAdapter<DesignVertex, DesignEdge, 
		DesignGraph, DesignVertexDto, DesignEdgeDto, DesignGraphDto> implements DesignGraphAdapter {

	@Override
	protected DesignGraph createGraph() {
		return new DesignGraph();
	}

	@Override
	protected DesignGraphDto createDto() {
		return new DesignGraphDto();
	}

	@Override
	protected DesignVertex createVertex() {
		return new DesignVertex();
	}

	@Override
	protected DesignVertexDto createVertexDto() {
		return new DesignVertexDto();
	}

	@Override
	protected void updateVertexFromDto(DesignVertex vertex, DesignVertexDto vertexDto) {
		vertex.setLabel(vertexDto.getLabel());
		vertex.setPosition(new Position(vertexDto.getX(), vertexDto.getY()));
	}

	@Override
	protected void updateVertexDtoFromVertex(DesignVertexDto vertexDto, DesignVertex vertex) {
		vertexDto.setLabel(vertex.getLabel());
		vertexDto.setX(vertex.getPosition().getX());
		vertexDto.setY(vertex.getPosition().getY());
	}

	@Override
	protected DesignEdge createEdge() {
		return new DesignEdge();
	}

	@Override
	protected DesignEdgeDto createEdgeDto() {
		return new DesignEdgeDto();
	}

	@Override
	protected void updateEdgeFromDto(DesignEdge edge, DesignEdgeDto edgeDto) {
		edge.setWeight(edgeDto.getWeight());
	}

	@Override
	protected void updateEdgeDtoFromEdge(DesignEdgeDto edgeDto, DesignEdge edge) {
		edgeDto.setWeight(edge.getWeight());
	}

}
