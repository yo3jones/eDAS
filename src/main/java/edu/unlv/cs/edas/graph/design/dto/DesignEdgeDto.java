package edu.unlv.cs.edas.graph.design.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.unlv.cs.edas.graph.dto.EdgeDto;

/**
 * A DTO POJO that represents an edge of a design graph. 
 * 
 * @author Chris Jones
 *
 */
public class DesignEdgeDto implements EdgeDto {

	/**
	 * A dummy member so this object can serialize.
	 */
	private String e = "e";
	
	/**
	 * Returns a dummy value.
	 * 
	 * @return A dummy value.
	 */
	public String getE() {
		return e;
	}
	
	/**
	 * Sets a dummy value.
	 * 
	 * @param e
	 *            A dummy value.
	 */
	public void setE(String e) {
		this.e = e;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (obj.getClass() != this.getClass()) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.toString();
	}
	
}
