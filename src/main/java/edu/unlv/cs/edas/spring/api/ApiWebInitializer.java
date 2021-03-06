package edu.unlv.cs.edas.spring.api;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import edu.unlv.cs.edas.spring.root.MongoConfig;
import edu.unlv.cs.edas.spring.root.RootConfig;
import edu.unlv.cs.edas.spring.root.SecurityConfig;

public class ApiWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected String getServletName() {
		return super.getServletName() + "Api";
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootConfig.class, MongoConfig.class, SecurityConfig.class};
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
