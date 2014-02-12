package edu.unlv.cs.edas.graph.design;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.unlv.cs.edas.graph.domain.Vertex;

/**
 * An implementation of {@link Vertex} for design graphs vertices.
 * 
 * @author Chris Jones
 * 
 */
public class DesignVertex implements Vertex {

	/**
	 * The label of this vertex.
	 */
	private String label;
	
	/**
	 * The position of this vertex.
	 */
	private Position position;
	
	/**
	 * Returns the label of this vertex.
	 * 
	 * @return The label of this vertex.
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Sets the label of this vertex.
	 * 
	 * @param label
	 *            The label of this vertex.
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * Returns the position of this vertex.
	 * 
	 * @return The position of this vertex.
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Sets the position of this vertex.
	 * 
	 * @param position
	 *            The position of this vertex.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (obj.getClass() != this.getClass()) return false;
		DesignVertex that = (DesignVertex) obj;
		return new EqualsBuilder()
			.append(this.label, that.label)
			.append(this.position, that.position)
			.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(label)
			.append(position)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("label", label)
			.append("position", position)
			.toString();
	}
	
}
