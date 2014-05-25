package edu.unlv.cs.edas.design.domain;

import org.bson.types.ObjectId;

public final class ImmutableRun implements Run {

	private Run mutableRun;
	
	public ImmutableRun(Run mutableRun) {
		this.mutableRun = mutableRun;
	}
	
	@Override
	public final ObjectId getId() {
		return mutableRun.getId();
	}

	@Override
	public final String getStringId() {
		return mutableRun.getStringId();
	}

	@Override
	public final ObjectId getOwnerId() {
		return mutableRun.getOwnerId();
	}

	@Override
	public final String getName() {
		return mutableRun.getName();
	}

	@Override
	public final ObjectId getGraphId() {
		return mutableRun.getGraphId();
	}

	@Override
	public final String getStringGraphId() {
		return mutableRun.getStringGraphId();
	}

	@Override
	public final ObjectId getAlgorithmId() {
		return mutableRun.getAlgorithmId();
	}

	@Override
	public final String getStringAlgorithmId() {
		return mutableRun.getStringAlgorithmId();
	}

}
