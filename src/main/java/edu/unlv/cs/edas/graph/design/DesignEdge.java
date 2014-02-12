package edu.unlv.cs.edas.graph.design;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.unlv.cs.edas.graph.domain.Edge;

/**
 * An implementation of {@link Edge} for edges on a design graph.
 * 
 * @author Chris Jones
 * 
 */
public class DesignEdge implements Edge {
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (obj.getClass() != this.getClass()) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.toString();
	}
	
}
