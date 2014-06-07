package edu.unlv.cs.edas.execute.manager.impl;

import static org.springframework.context.annotation.ScopedProxyMode.INTERFACES;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import edu.unlv.cs.edas.execute.domain.Execution;
import edu.unlv.cs.edas.execute.manager.ExecutionsStore;

@Component @Scope(value="session", proxyMode=INTERFACES)
public class ExecutionStoreImpl implements ExecutionsStore, Serializable {

	private static final long serialVersionUID = 1547827486004092536L;

	private ObjectId id;
	private Execution execution;
	
	@Override
	public Execution get(ObjectId id) {
		Execution execution = null;
		if (contains(id)) {
			execution = this.execution;
		}
		return execution;
	}

	@Override
	public boolean contains(ObjectId id) {
		return id.equals(this.id);
	}
	
	@Override
	public void put(ObjectId id, Execution execution) {
		this.id = id;
		this.execution = execution;
	}
	
	@Override
	public void clear() {
		id = null;
		execution = null;
	}
	
}
