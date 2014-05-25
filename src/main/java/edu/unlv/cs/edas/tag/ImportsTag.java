package edu.unlv.cs.edas.tag;

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.tag.common.core.ImportSupport;

public class ImportsTag extends ImportSupport {

	private static final long serialVersionUID = -3070610890316164503L;

	@Override
	public int doStartTag() throws JspException {
		super.url = "/common/imports";
		return super.doStartTag();
	}
	
}
