package edu.unlv.cs.edas.design.domain;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * An implementation of {@link Edge} for edges on a design graph.
 * 
 * @author Chris Jones
 * 
 */
@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=NONE)
public class DesignEdge {
	
	/**
	 * The weight of this edge.
	 */
	private Integer weight;
	
	public DesignEdge() {
		
	}
	
	public DesignEdge(Integer weight) {
		this.weight = weight;
	}
	
	/**
	 * Returns the weight of this edge.
	 * 
	 * @return The weight of this edge.
	 */
	public Integer getWeight() {
		return weight;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (obj.getClass() != this.getClass()) return false;
		DesignEdge that = (DesignEdge) obj; 
		return new EqualsBuilder()
			.append(this.weight, that.weight)
			.isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("weight", weight)
			.toString();
	}
	
}
