package edu.unlv.cs.edas.graph.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

/**
 * A key for vertices in graphs meant for eDAS application.
 * 
 * @author Chris Jones
 * 
 */
public class Key {

	/**
	 * The value of the key.
	 */
	private Integer id;
	
	/**
	 * Constructs a vertex key with the given name.
	 * 
	 * @param name
	 *            The name of the vertex.
	 * @throws IllegalArgumentException
	 *             If the name is null.
	 */
	public Key(Integer id) throws IllegalArgumentException {
		Assert.notNull(id);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (obj.getClass() != this.getClass()) return false;
		Key that = (Key) obj;
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
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.toString();
	}
	
}
