package edu.unlv.cs.edas.execute.manager.impl;

import static org.springframework.context.annotation.ScopedProxyMode.INTERFACES;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.unlv.cs.edas.execute.domain.Execution;
import edu.unlv.cs.edas.execute.manager.ExecutionsStore;

@Component @Scope(value="session", proxyMode=INTERFACES)
public class ExecutionStoreImpl implements ExecutionsStore, Serializable {

	private static final long serialVersionUID = 1547827486004092536L;

	private Map<ObjectId, Execution> executions = new HashMap<>();
	private Map<ObjectId, ObjectId> executionIdsByAlgorithmId = new HashMap<>();
	private Map<ObjectId, ObjectId> executionIdsByGraphDetailsId = new HashMap<>();
	
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
		executionIdsByAlgorithmId.put(execution.getAlgorithm().getId(), id);
		executionIdsByGraphDetailsId.put(execution.getDesignGraphDetails().getId(), id);
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
			remove(executionIdsByAlgorithmId.get(algorithmId));
		}
	}
	
	@Override
	public void removeByGraphDetailsId(ObjectId graphDetailsId) {
		if (executionIdsByGraphDetailsId.containsKey(graphDetailsId)) {
			remove(executionIdsByGraphDetailsId.get(graphDetailsId));
		}
	}
	
}
