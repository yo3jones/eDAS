package edu.unlv.cs.edas.design.controller;

import java.util.Collection;

import edu.unlv.cs.edas.design.domain.DesignGraphDetails;

public class AllDesignGraphDetailsModel {

	private Collection<DesignGraphDetails> graphDetails;
	
	public Collection<DesignGraphDetails> getGraphDetails() {
		return graphDetails;
	}
	
	public AllDesignGraphDetailsModel setGraphDetails(Collection<DesignGraphDetails> graphDetails) {
		this.graphDetails = graphDetails;
		return this;
	}
	
}
