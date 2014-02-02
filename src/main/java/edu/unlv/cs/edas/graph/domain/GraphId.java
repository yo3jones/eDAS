package edu.unlv.cs.edas.graph.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.Assert;

public class GraphId {

	private Long id;
	
	public GraphId(Long id) {
		Assert.notNull(id);
		this.id = id;
	}
	
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
