package edu.unlv.cs.edas.design.domain;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * This is a immutable POJO that represents two dimensional coordinate.
 * 
 * @author Chris Jones
 * 
 */
@JsonAutoDetect(fieldVisibility=ANY, getterVisibility=NONE)
public class Position {

	/**
	 * The x coordinate of this position.
	 */
	private Double x;
	
	/**
	 * The y coordinate of this position.
	 */
	private Double y;
	
	Position() {
		
	}
	
	/**
	 * Constructs a position with the given x and y coordinate.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 */
	public Position(Double x, Double y) {
		Assert.notNull(x);
		Assert.notNull(y);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the x coordinate of this position.
	 * 
	 * @return The x coordinate of this position.
	 */
	public Double getX() {
		return x;
	}
	
	/**
	 * Returns the y coordinate of this position.
	 * 
	 * @return The y coordinate of this position.
	 */
	public Double getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("x", x)
			.append("y", y)
			.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {return false;}
		if (obj == this) {return true;}
		if (obj.getClass() != this.getClass()) {return false;}
		Position that = (Position) obj;
		return new EqualsBuilder()
			.append(this.x, that.x)
			.append(this.y, that.y)
			.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(x)
			.append(y)
			.toHashCode();
	}
	
}
