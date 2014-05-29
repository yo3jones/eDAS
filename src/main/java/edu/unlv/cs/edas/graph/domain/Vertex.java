package edu.unlv.cs.edas.graph.domain;

import java.io.Serializable;

import edu.unlv.cs.edas.design.domain.Position;

public interface Vertex extends Serializable {

	Position getPosition();
	
	String getLabel();
	
}
