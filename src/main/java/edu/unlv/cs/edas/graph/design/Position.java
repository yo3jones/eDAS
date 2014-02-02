package edu.unlv.cs.edas.graph.design;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

public class Position {

	private Integer x;
	private Integer y;
	
	Position() {
		
	}
	
	public Position(Integer x, Integer y) {
		Assert.notNull(x);
		Assert.notNull(y);
		this.x = x;
		this.y = y;
	}
	
	public Integer getX() {
		return x;
	}
	
	public Integer getY() {
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
