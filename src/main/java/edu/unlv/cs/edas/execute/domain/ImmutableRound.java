package edu.unlv.cs.edas.execute.domain;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=NONE)
public final class ImmutableRound implements Round {

	private static final long serialVersionUID = -3627234090312907201L;

	@JsonUnwrapped
	private final MutableRound round;
	
	public ImmutableRound(MutableRound round) {
		this.round = round;
	}
	
	@Override
	public final Integer getMessageCount() {
		return round.getMessageCount();
	}

	@Override
	public final Integer getPreviousMessageCount() {
		return round.getPreviousMessageCount();
	}
	
	@Override
	public final ExecutionGraph getGraph() {
		return new ExecutionUnmodifiableGraph(round.getGraph());
	}

}
