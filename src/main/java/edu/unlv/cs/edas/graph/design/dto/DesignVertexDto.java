package edu.unlv.cs.edas.graph.design.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.unlv.cs.edas.graph.dto.VertexDto;

/**
 * An implementation of {@link VertexDto} for vertices of a design graph.
 * 
 * @author Chris Jones
 * 
 */
public class DesignVertexDto implements VertexDto {

	/**
	 * The label of this vertex.
	 */
	private String label;
	
	/**
	 * The x position of this vertex.
	 */
	private Integer x;
	
	/**
	 * The y position of this vertex.
	 */
	private Integer y;
	
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
	 * Returns the x position of this vertex.
	 * 
	 * @return The x position of this vertex.
	 */
	public Integer getX() {
		return x;
	}
	
	/**
	 * Sets the x position of this vertex.
	 * 
	 * @param x
	 *            The x position of this vertex.
	 */
	public void setX(Integer x) {
		this.x = x;
	}
	
	/**
	 * Returns the y position of this vertex.
	 * 
	 * @return The y position of this vertex.
	 */
	public Integer getY() {
		return y;
	}
	
	/**
	 * Sets the y position of this vertex.
	 * 
	 * @param y
	 *            The y position of this vertex.
	 */
	public void setY(Integer y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (obj.getClass() != this.getClass()) return false;
		DesignVertexDto that = (DesignVertexDto) obj;
		return new EqualsBuilder()
			.append(this.label, that.label)
			.append(this.x, that.x)
			.append(this.y, that.y)
			.isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("label", label)
			.append("x", x)
			.append("y", y)
			.toString();
	}
	
}
