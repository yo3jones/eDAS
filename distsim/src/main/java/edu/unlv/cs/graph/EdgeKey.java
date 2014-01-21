package edu.unlv.cs.graph;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

/**
 * Represents an edge between two vertices identified by their keys.
 * 
 * @author Chris Jones
 * 
 * @param <K>
 *            The unique identifier of the vertices.
 */
public final class EdgeKey<K> {

	/**
	 * The key that uniquely identifies the start vertex of the edge.
	 */
	private K fromKey;
	
	/**
	 * The key that uniquely identifies the end vertex of the edge.
	 */
	private K toKey;
	
	/**
	 * Creates an edge key between two vertices identified by fromKey and
	 * toKey.
	 * 
	 * @param fromKey
	 *            The key that uniquely identified the start vertex of the
	 *            edge.
	 * @param toKey
	 *            The key that uniquely identifies the end vertex of the
	 *            edge.
	 */
	public EdgeKey(K fromKey, K toKey) {
		Assert.notNull(fromKey);
		Assert.notNull(toKey);
		this.fromKey = fromKey;
		this.toKey = toKey;
	}
	
	/**
	 * Returns the identifier of the start of an edge.
	 * 
	 * @return The identifier of the start of an edge.
	 */
	public K getFromKey() {
		return fromKey;
	}
	
	/**
	 * Returns the identifier of the end of an edge.
	 * 
	 * @return The identifier of the end of an edge.
	 */
	public K getToKey() {
		return toKey;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) { return false; }
		EdgeKey<?> that = (EdgeKey<?>) obj;
		return new EqualsBuilder()
				.append(this.fromKey, that.fromKey)
				.append(this.toKey, that.toKey)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(fromKey)
				.append(toKey)
				.toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("fromKey", fromKey)
				.append("toKey", toKey)
				.toString();
	}
	
}
