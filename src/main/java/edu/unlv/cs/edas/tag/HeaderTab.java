package edu.unlv.cs.edas.tag;

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.tag.common.core.ImportSupport;

public class HeaderTab extends ImportSupport {

	private static final long serialVersionUID = 3208444346295546267L;

	@Override
	public int doStartTag() throws JspException {
		this.url = "/common/header";
		return super.doStartTag();
	}
	
}
