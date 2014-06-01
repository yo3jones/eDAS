package edu.unlv.cs.edas.execute.manager.impl;

import static org.springframework.context.annotation.ScopedProxyMode.INTERFACES;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.unlv.cs.edas.execute.domain.Execution;
import edu.unlv.cs.edas.execute.manager.ExecutionsStore;

@Component @Scope(value="session", proxyMode=INTERFACES)
public class ExecutionStoreImpl implements ExecutionsStore, Serializable {

	private static final long serialVersionUID = 1547827486004092536L;

	private Map<ObjectId, Execution> executions = new HashMap<>();
	private Map<ObjectId, Set<ObjectId>> executionIdsByAlgorithmId = new HashMap<>();
	private Map<ObjectId, Set<ObjectId>> executionIdsByGraphDetailsId = new HashMap<>();
	
	@Override
	public Execution get(ObjectId id) {
		return executions.get(id);
	}

	@Override
	public boolean contains(ObjectId id) {
		return executions.containsKey(id);
	}
	
	@Override
	public void put(ObjectId id, Execution execution) {
		executions.put(id, execution);
		
		ObjectId algorithmId = execution.getAlgorithm().getId();
		Set<ObjectId> algorithmIdSet = executionIdsByAlgorithmId.get(algorithmId);
		if (algorithmIdSet == null) {
			algorithmIdSet = new HashSet<>();
			executionIdsByAlgorithmId.put(algorithmId, algorithmIdSet);
		}
		algorithmIdSet.add(id);
		
		ObjectId graphId = execution.getDesignGraphDetails().getId();
		Set<ObjectId> graphIdSet = executionIdsByGraphDetailsId.get(graphId);
		if (graphIdSet == null) {
			graphIdSet = new HashSet<>();
			executionIdsByGraphDetailsId.put(graphId, graphIdSet);
		}
		graphIdSet.add(id);
	}

	@Override
	public void remove(ObjectId id) {
		if (!executions.containsKey(id)) {
			return;
		}
		Execution execution = executions.get(id);
		executions.remove(id);
		executionIdsByAlgorithmId.remove(execution.getAlgorithm().getId());
		executionIdsByGraphDetailsId.remove(execution.getDesignGraphDetails().getId());
	}

	@Override
	public void removeByAlgorithmId(ObjectId algorithmId) {
		if (executionIdsByAlgorithmId.containsKey(algorithmId)) {
			Set<ObjectId> executionIds = executionIdsByAlgorithmId.get(algorithmId);
			for (ObjectId executionId : executionIds) {
				remove(executionId);
			}
		}
	}
	
	@Override
	public void removeByGraphDetailsId(ObjectId graphDetailsId) {
		if (executionIdsByGraphDetailsId.containsKey(graphDetailsId)) {
			Set<ObjectId> executionIds = executionIdsByGraphDetailsId.get(graphDetailsId);
			for (ObjectId executionId : executionIds) {
				remove(executionId);
			}
		}
	}
	
}
