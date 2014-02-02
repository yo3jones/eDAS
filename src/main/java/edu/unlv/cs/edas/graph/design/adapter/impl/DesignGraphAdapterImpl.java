package edu.unlv.cs.edas.graph.design.adapter.impl;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.unlv.cs.edas.graph.adapter.impl.AbstractGraphAdapter;
import edu.unlv.cs.edas.graph.design.DesignEdge;
import edu.unlv.cs.edas.graph.design.DesignGraph;
import edu.unlv.cs.edas.graph.design.DesignGraphDto;
import edu.unlv.cs.edas.graph.design.DesignVertex;
import edu.unlv.cs.edas.graph.design.adapter.DesignGraphAdapter;

@Component @Scope(SCOPE_PROTOTYPE)
public class DesignGraphAdapterImpl extends AbstractGraphAdapter<DesignVertex, DesignEdge, 
		DesignGraph, DesignGraphDto> implements DesignGraphAdapter {

	@Override
	protected DesignGraph createGraph() {
		return new DesignGraph();
	}

	@Override
	protected DesignGraphDto createDto() {
		return new DesignGraphDto();
	}

}
