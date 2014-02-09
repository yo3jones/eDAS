package edu.unlv.cs.edas.spring.api;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import edu.unlv.cs.edas.spring.root.RootConfig;

public class ApiWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected String getServletName() {
		return super.getServletName() + "Api";
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {ApiWebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/api/*"};
	}

}
