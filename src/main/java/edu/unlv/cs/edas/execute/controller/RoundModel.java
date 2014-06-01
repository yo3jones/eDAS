package edu.unlv.cs.edas.execute.controller;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import edu.unlv.cs.edas.execute.domain.Round;

@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=NONE)
public class RoundModel {

	@JsonUnwrapped
	private Round round;
	
	private List<String> log;
	
	public RoundModel() {
		
	}
	
	public Round getRound() {
		return round;
	}
	
	public RoundModel setRound(Round round) {
		this.round = round;
		return this;
	}
	
	public RoundModel setLog(List<String> log) {
		this.log = log;
		return this;
	}
	
	public List<String> getLog() {
		return log;
	}
	
}
