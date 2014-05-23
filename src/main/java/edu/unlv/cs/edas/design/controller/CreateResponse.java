package edu.unlv.cs.edas.design.controller;

/**
 * A response object when creating a design graph.
 * 
 * @author Chris Jones
 * 
 */
public class CreateResponse {

	private String id;
	
	public String getId() {
		return id;
	}
	
	public CreateResponse setId(String id) {
		this.id = id;
		return this;
	}
	
}
