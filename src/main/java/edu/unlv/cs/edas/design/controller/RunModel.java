package edu.unlv.cs.edas.design.controller;

import java.util.Collection;

import edu.unlv.cs.edas.design.domain.Algorithm;
import edu.unlv.cs.edas.design.domain.DesignGraphDetails;
import edu.unlv.cs.edas.design.domain.ImmutableAlgorithm;
import edu.unlv.cs.edas.design.domain.Run;

public class RunModel {

	private Run run;
	private DesignGraphDetails graph;
	private Collection<DesignGraphDetails> graphs;
	private Algorithm algorithm;
	private Collection<ImmutableAlgorithm> algorithms;
	
	public Run getRun() {
		return run;
	}
	
	public void setRun(Run run) {
		this.run = run;
	}
	
	public String getGraphName() {
		return graph == null ? "" : graph.getName();
	}
	
	public String getGraphId() {
		return graph == null ? "" : graph.getStringId();
	}
	
	public void setGraph(DesignGraphDetails graph) {
		this.graph = graph;
	}
	
	public Collection<DesignGraphDetails> getGraphs() {
		return graphs;
	}
	
	public void setGraphs(Collection<DesignGraphDetails> graphs) {
		this.graphs = graphs;
	}
	
	public String getAlgorithmName() {
		return algorithm == null ? "" : algorithm.getName();
	}
	
	public String getAlgorithmId() {
		return algorithm == null ? "" : algorithm.getStringId();
	}
	
	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}
	
	public Collection<ImmutableAlgorithm> getAlgorithms() {
		return algorithms;
	}
	
	public void setAlgorithms(Collection<ImmutableAlgorithm> algorithms) {
		this.algorithms = algorithms;
	}
	
}
