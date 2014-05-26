package edu.unlv.cs.edas.design.controller;

import java.util.Collection;

import edu.unlv.cs.edas.design.domain.ImmutableDesignGraphDetails;

public class AllDesignGraphDetailsModel {

	private Collection<ImmutableDesignGraphDetails> graphDetails;
	
	public Collection<ImmutableDesignGraphDetails> getGraphDetails() {
		return graphDetails;
	}
	
	public AllDesignGraphDetailsModel setGraphDetails(
			Collection<ImmutableDesignGraphDetails> graphDetails) {
		this.graphDetails = graphDetails;
		return this;
	}
	
}
