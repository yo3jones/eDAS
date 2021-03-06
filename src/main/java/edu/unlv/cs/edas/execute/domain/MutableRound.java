package edu.unlv.cs.edas.execute.domain;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=NONE)
public class MutableRound implements Round {

	private static final long serialVersionUID = 6853413834769675173L;
	
	private Integer messageCount;
	
	private Integer previousMessageCount;
	
	private ExecutionGraph graph;
	
	public MutableRound() {
		
	}
	
	public MutableRound(Round round) {
		this.messageCount = round.getMessageCount();
		this.previousMessageCount = round.getPreviousMessageCount();
		this.graph = new ExecutionHashGraph(round.getGraph());
	}
	
	@Override
	public Integer getMessageCount() {
		return messageCount;
	}
	
	public void setMessageCount(Integer messageCount) {
		this.messageCount = messageCount;
	}
	
	public void incrementMessageCount(Integer increment) {
		messageCount += increment;
	}
	
	@Override
	public Integer getPreviousMessageCount() {
		return previousMessageCount;
	}
	
	public void setPreviousMessageCount(Integer previousMessageCount) {
		this.previousMessageCount = previousMessageCount;
	}
	
	@Override
	public ExecutionGraph getGraph() {
		return graph;
	}

	public void setGraph(ExecutionGraph graph) {
		this.graph = graph;
	}
	
}
