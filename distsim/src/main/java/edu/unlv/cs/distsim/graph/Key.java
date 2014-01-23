package edu.unlv.cs.distsim.graph;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

/**
 * A key for vertices in graphs meant for the distributed algorithm simulator.
 * 
 * @author Chris Jones
 * 
 */
public final class Key {

	String name;
	
	/**
	 * Constructs a vertex key with the given name.
	 * 
	 * @param name
	 *            The name of the vertex.
	 * @throws IllegalArgumentException
	 *             If the name is null.
	 */
	public Key(String name) throws IllegalArgumentException {
		Assert.notNull(name);
		this.name = name;
	}
	
	/**
	 * Returns the name of the vertex.
	 * 
	 * @return The name of the vertex.
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (obj.getClass() != this.getClass()) return false;
		Key that = (Key) obj;
		return new EqualsBuilder()
			.append(this.name, that.name)
			.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(name)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("name", name)
			.toString();
	}
	
}
