package edu.unlv.cs.edas.graph.design.manager.impl;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.unlv.cs.edas.graph.design.DesignEdge;
import edu.unlv.cs.edas.graph.design.DesignGraph;
import edu.unlv.cs.edas.graph.design.DesignGraphId;
import edu.unlv.cs.edas.graph.design.DesignVertex;
import edu.unlv.cs.edas.graph.design.manager.DesignGraphManager;
import edu.unlv.cs.edas.graph.manager.impl.AbstractGraphManager;

/**
 * An implementation of {@link AbstractGraphManager} for design graphs.
 * 
 * @author Chris Jones
 *
 */
@Component @Scope(SCOPE_SINGLETON)
public class DesignGraphManagerImpl extends AbstractGraphManager<DesignGraphId, DesignVertex, 
		DesignEdge, DesignGraph> implements DesignGraphManager {

	private AtomicLong currentId = new AtomicLong(1001L);
	
	@Override
	protected DesignGraphId createNextId() {
		return new DesignGraphId(currentId.getAndIncrement());
	}

}
