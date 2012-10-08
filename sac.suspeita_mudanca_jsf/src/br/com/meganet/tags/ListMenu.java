package br.com.meganet.tags;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class ListMenu implements Tag, Serializable{

	private static final long serialVersionUID = -5331262015965089909L;
	private PageContext pc = null;
	private Tag parent = null;
	private String name = null;
	
	
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public int doStartTag() throws JspException {
		try {

			if(name != null) {
				pc.getOut().write("Hello " + name + "!");
			} else {
				pc.getOut().write("You didn't enter your name");
				pc.getOut().write(", what are you afraid of ?");
			}

			} catch(IOException e) {
				throw new JspTagException("An IOException occurred.");
			}
			return SKIP_BODY;	}

	public Tag getParent() {
		return parent;
	}

	public void release() {
		pc = null;
		parent = null;
		name = null;		
	}

	public void setPageContext(PageContext arg0) {
		pc = arg0;
	}

	public void setParent(Tag arg0) {
		parent = arg0;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public String getName() {
		return name;
	}


}
