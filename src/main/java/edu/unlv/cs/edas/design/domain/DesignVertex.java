package edu.unlv.cs.edas.design.domain;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * An implementation of {@link Vertex} for design graphs vertices.
 * 
 * @author Chris Jones
 * 
 */
@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=NONE)
public class DesignVertex {

	/**
	 * The label of this vertex.
	 */
	private String label;
	
	/**
	 * The position of this vertex.
	 */
	@JsonUnwrapped
	private Position position;
	
	DesignVertex() {
		
	}
	
	public DesignVertex(String label, Position position) {
		this.label = label;
		this.position = position;
	}
	
	/**
	 * Returns the label of this vertex.
	 * 
	 * @return The label of this vertex.
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Returns the position of this vertex.
	 * 
	 * @return The position of this vertex.
	 */
	public Position getPosition() {
		return position;
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
