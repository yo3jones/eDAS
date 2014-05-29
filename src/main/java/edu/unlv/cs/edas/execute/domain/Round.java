package edu.unlv.cs.edas.execute.domain;

import java.io.Serializable;

public interface Round extends Serializable {

	Integer getMessageCount();
	
	ExecutionGraph getGraph();
	
}
