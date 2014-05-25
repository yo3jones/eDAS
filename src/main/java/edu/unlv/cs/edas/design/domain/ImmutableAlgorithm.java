package edu.unlv.cs.edas.design.domain;

import org.bson.types.ObjectId;

public final class ImmutableAlgorithm implements Algorithm {

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

}
