package edu.unlv.cs.edas.graph.design.controller.api.response;

/**
 * A response object when creating a design graph.
 * 
 * @author Chris Jones
 * 
 */
public class CreateResponse {

	/**
	 * The id of the graph created.
	 */
	private Long id;
	
	/**
	 * Returns the id of the graph created.
	 * 
	 * @return The id of the graph created.
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the id of the graph created.
	 * 
	 * @param id
	 *            The id of the graph created.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
}
