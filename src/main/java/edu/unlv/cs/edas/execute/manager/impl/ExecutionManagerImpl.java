package edu.unlv.cs.edas.execute.manager.impl;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.unlv.cs.edas.design.domain.DesignEdge;
import edu.unlv.cs.edas.design.domain.DesignGraph;
import edu.unlv.cs.edas.design.domain.ImmutableAlgorithm;
import edu.unlv.cs.edas.design.domain.ImmutableDesignGraphDetails;
import edu.unlv.cs.edas.design.domain.ImmutableRun;
import edu.unlv.cs.edas.design.domain.MutableDesignGraphDetails;
import edu.unlv.cs.edas.design.manager.AlgorithmManager;
import edu.unlv.cs.edas.design.manager.DesignGraphDetailsManager;
import edu.unlv.cs.edas.design.manager.RunManager;
import edu.unlv.cs.edas.execute.domain.Execution;
import edu.unlv.cs.edas.execute.manager.ExecutionManager;
import edu.unlv.cs.edas.execute.manager.ExecutionsStore;
import edu.unlv.cs.graph.EdgeKey;

@Component @Scope(value="prototype")
public class ExecutionManagerImpl implements ExecutionManager, Serializable {

	private static final long serialVersionUID = 9143679730408672011L;

	@Autowired RunManager runManager;
	
	@Autowired AlgorithmManager algorithmManager;
	
	@Autowired DesignGraphDetailsManager graphDetailsManager;
	
	@Autowired ExecutionsStore executions;
	
	@Override
	public Execution get(ObjectId id) {
		if (executions.contains(id)) {
			return executions.get(id);
		}
		ImmutableRun run = runManager.get(id);
		ImmutableAlgorithm algorithm = algorithmManager.get(run.getAlgorithmId());
		
		ImmutableDesignGraphDetails graphDetails = graphDetailsManager.get(run.getGraphId());
		if (algorithm.getBidirectional()) {
			 MutableDesignGraphDetails mutableGraphDetails = new MutableDesignGraphDetails(
					 graphDetails);
			 DesignGraph bidirectedGraph = mutableGraphDetails.getGraph();
			 for (EdgeKey<Integer> edgeKey : graphDetails.getGraph().getEdgeSet()) {
				 DesignEdge edge = graphDetails.getGraph().getEdge(edgeKey);
				 bidirectedGraph.putEdge(
						 new EdgeKey<Integer>(edgeKey.getToKey(), edgeKey.getFromKey()), edge);
			 }
			 graphDetails = new ImmutableDesignGraphDetails(mutableGraphDetails);
		}
		
		Execution execution = new Execution(run, algorithm, graphDetails);
		executions.put(id, execution);
		return execution;
	}

	@Override
	public Execution get(String id) {
		return get(new ObjectId(id));
	}
	
	@Override
	public void clear(ObjectId id) {
		executions.remove(id);
	}

	@Override
	public void clear(String id) {
		clear(new ObjectId(id));
	}
	
}
