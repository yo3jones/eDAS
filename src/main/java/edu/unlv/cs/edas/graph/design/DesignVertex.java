package edu.unlv.cs.edas.graph.design;

import edu.unlv.cs.edas.graph.domain.Key;
import edu.unlv.cs.edas.graph.domain.Vertex;

public class DesignVertex extends Vertex {

	private Position position;

	protected DesignVertex() {
		super();
	}
	
	public DesignVertex(Key id) throws IllegalArgumentException {
		super(id);
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
}
