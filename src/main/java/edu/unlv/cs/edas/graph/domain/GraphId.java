package edu.unlv.cs.edas.graph.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

/**
 * An immutable POJO that represents a unique identifier of a graph within the
 * eDAS application.
 * 
 * @author Chris Jones
 * 
 */
public class GraphId {

	/**
	 * The unique value.
	 */
	private Long id;
	
	/**
	 * Constructs a graph ID with the given value.
	 * 
	 * @param id
	 *            value of the ID.
	 */
	public GraphId(Long id) {
		Assert.notNull(id);
		this.id = id;
	}
	
	/**
	 * Returns the value of this ID.
	 * 
	 * @return The value of this ID.
	 */
	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {return false;}
		if (obj == this) {return true;}
		if (obj.getClass() != this.getClass()) {return false;}
		GraphId that = (GraphId) obj;
		return new EqualsBuilder()
			.append(this.id, that.id)
			.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(id)
			.toHashCode();
	}
	
}
