package edu.unlv.cs.edas.design.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * An implementation of {@link Edge} for edges on a design graph.
 * 
 * @author Chris Jones
 * 
 */
public class DesignEdge {
	
	/**
	 * The weight of this edge.
	 */
	private Integer weight;
	
	/**
	 * Returns the weight of this edge.
	 * 
	 * @return The weight of this edge.
	 */
	public Integer getWeight() {
		return weight;
	}
	
	/**
	 * Sets the weight of this edge.
	 * 
	 * @param weight
	 *            The weight of this edge.
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
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
