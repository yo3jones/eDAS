package edu.unlv.cs.edas.execute.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.list.UnmodifiableList;

import edu.unlv.cs.edas.design.domain.ImmutableAlgorithm;
import edu.unlv.cs.edas.design.domain.ImmutableDesignGraphDetails;
import edu.unlv.cs.edas.design.domain.ImmutableRun;

public class Execution implements Serializable {

	private static final long serialVersionUID = -2535682519168569704L;
	
	private ImmutableRun run;
	
	private ImmutableAlgorithm algorithm;
	
	private ImmutableDesignGraphDetails designGraphDetails;
	
	private List<ImmutableRound> rounds;
	
	private List<String> log;
	
	public Execution(ImmutableRun run, ImmutableAlgorithm algorithm, 
			ImmutableDesignGraphDetails designGraphDetails) {
		this.run = run;
		this.algorithm = algorithm;
		this.designGraphDetails = designGraphDetails;
		rounds = new ArrayList<>();
		log = new ArrayList<>();
	}
	
	public ImmutableRun getRun() {
		return run;
	}
	
	public ImmutableAlgorithm getAlgorithm() {
		return algorithm;
	}
	
	public ImmutableDesignGraphDetails getDesignGraphDetails() {
		return designGraphDetails;
	}
	
	public ImmutableRound getCurrentRound() {
		return rounds.get(rounds.size() - 1);
	}
	
	public ImmutableRound getRound(Integer round) {
		return rounds.get(round);
	}
	
	public void addRound(ImmutableRound round) {
		rounds.add(round);
	}
	
	public int getRoundCount() {
		return rounds.size();
	}
	
	public void clearLog() {
		log.clear();
	}
	
	public void log(String message) {
		log.add(message);
	}
	
	public List<String> getLog() {
		return UnmodifiableList.unmodifiableList(log);
	}
	
}
