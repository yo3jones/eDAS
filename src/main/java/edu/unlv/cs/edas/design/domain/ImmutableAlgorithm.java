package edu.unlv.cs.edas.design.domain;

import org.bson.types.ObjectId;

public final class ImmutableAlgorithm implements Algorithm {

	private static final long serialVersionUID = 3060340419328184396L;
	
	private Algorithm mutableAlgorithm;
	
	public ImmutableAlgorithm(Algorithm mutableAlgorithm) {
		this.mutableAlgorithm = mutableAlgorithm;
	}
	
	@Override
	public final ObjectId getId() {
		return mutableAlgorithm.getId();
	}

	@Override
	public final ObjectId getOwnerId() {
		return mutableAlgorithm.getOwnerId();
	}
	
	@Override
	public final String getStringId() {
		return mutableAlgorithm.getStringId();
	}

	@Override
	public final String getName() {
		return mutableAlgorithm.getName();
	}

	@Override
	public final String getAlgorithm() {
		return mutableAlgorithm.getAlgorithm();
	}

	@Override
	public final String getStateDisplayPattern() {
		return mutableAlgorithm.getStateDisplayPattern();
	}
	
	@Override
	public final String getMessageDisplayPattern() {
		return mutableAlgorithm.getMessageDisplayPattern();
	}
	
	@Override
	public final Boolean getBidirectional() {
		return mutableAlgorithm.getBidirectional();
	}
	
}
